package com.example.push.notification;

import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRepository;
import com.example.goinggoingdomain.domain.user.User;
import com.example.push.message.FirebaseCloudMessageService;
import com.example.push.config.redissen.DistributeLock;
import com.example.push.device.DeviceToken;
import com.example.push.device.service.DeviceTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class SchedulingService {

    private final PersonalScheduleRepository personalScheduleRepository;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    private final DeviceTokenService deviceTokenService;


    @Autowired
    public SchedulingService(PersonalScheduleRepository personalScheduleRepository, FirebaseCloudMessageService firebaseCloudMessageService, DeviceTokenService deviceTokenService) {
        this.personalScheduleRepository = personalScheduleRepository;
        this.firebaseCloudMessageService = firebaseCloudMessageService;
        this.deviceTokenService = deviceTokenService;
    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void sendScheduleStartNotifications() throws IOException {
        List<PersonalSchedule> personalScheduleEntities = personalScheduleRepository.findByScheduleStartTimeBeforeAndScheduleNotificationStart(LocalDateTime.now(), false);
        for (PersonalSchedule personalSchedule : personalScheduleEntities) {
            updateNotificationStart(personalSchedule);
            sendStartNotifications(personalSchedule);
        }

    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void sendScheduleDoneNotifications() throws IOException {
        List<PersonalSchedule> personalScheduleEntities = personalScheduleRepository.findByScheduleDoneTimeBeforeAndScheduleNotificationDone(LocalDateTime.now(), false);
        for (PersonalSchedule personalSchedule : personalScheduleEntities) {
            updateNotificationDone(personalSchedule);
            sendDoneNotifications(personalSchedule);
        }
    }

    @DistributeLock(key = "#personalSchedule.personalScheduleId")
    protected void updateNotificationStart(PersonalSchedule personalSchedule) {
        personalSchedule.updateNotificationStart(true);
    }
    @DistributeLock(key = "#personalSchedule.personalScheduleId")
    protected void updateNotificationDone(PersonalSchedule personalSchedule) {
        personalSchedule.updateNotificationDone(true);
    }
    @Async
    public void sendStartNotifications(PersonalSchedule personalSchedule) throws IOException {
        List<PersonalSchedule> personalScheduleList = personalScheduleRepository.findAllByUserUserIdWithSchedule(personalSchedule.getGroupSchedule().getScheduleId());
        for (PersonalSchedule tempPersonalSchedule : personalScheduleList) {
            User user = tempPersonalSchedule.getUser();
            if (user != null) {
                Long userId = user.getUserId();
                DeviceToken deviceToken = deviceTokenService.getDeviceToken(String.valueOf(userId));
                if (deviceToken != null) {
                    String userName = user.getUserNickname();
                    firebaseCloudMessageService.sendMessageTo(deviceToken.getDeviceToken(), "친구가 일정을 시작했어요", userName + "님이 일정을 시작했어요");
                }
            }
        }
    }

    @Async
    public void sendDoneNotifications(PersonalSchedule personalSchedule) throws IOException {
        List<PersonalSchedule> personalScheduleList = personalScheduleRepository.findAllByUserUserIdWithSchedule(personalSchedule.getGroupSchedule().getScheduleId());
        for (PersonalSchedule tempPersonalSchedule : personalScheduleList) {
            User user = tempPersonalSchedule.getUser();
            if (user != null) {
                Long userId = user.getUserId();
                DeviceToken deviceToken = deviceTokenService.getDeviceToken(String.valueOf(userId));
                if (deviceToken != null) {
                    String userName = user.getUserNickname();
                    firebaseCloudMessageService.sendMessageTo(deviceToken.getDeviceToken(), "친구가 일정을 종료했어요", userName + "님이 일정을 종료했어요");
                }
            }
        }
    }
}
