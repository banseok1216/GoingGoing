package com.example.schedule;

import com.example.group.model.Group;
import com.example.message.FirebaseCloudMessageService;
import com.example.scheduling.SchedulingService;
import com.example.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleHandler {
    private final SchedulingService schedulingService;
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Getter
    private enum NotificationType {
        START("일정 시작"),
        DONE("일정 종료");

        private final String message;
        NotificationType(String message) {
            this.message = message;
        }
    }

    @Scheduled(fixedRate = 1000)
    public void sendScheduleStartNotifications() {
        sendNotifications(schedulingService.getScheduleStartTargetList(), NotificationType.START);
    }

    @Scheduled(fixedRate = 1000)
    public void sendScheduleDoneNotifications() {
        sendNotifications(schedulingService.getScheduleDoneTargetList(), NotificationType.DONE);
    }

    private void sendNotifications(List<Group> groupList, NotificationType notificationType) {
        groupList.forEach(group -> {
            Group savedGroup = modifyScheduleNotifications(group, notificationType);
            savedGroup.getPersonalSchedules().forEach(personalSchedule -> {
                User user = personalSchedule.getUser();
                String deviceToken = user.getDeviceToken();
                if (deviceToken != null) {
                    try {
                        firebaseCloudMessageService.sendMessageTo(deviceToken, notificationType.getMessage(), user.getUserNickname() + "님의 일정이 " + notificationType.getMessage() + "되었습니다.");
                    } catch (IOException e) {
                        log.error("Firebase 메시지 전송 중 예외 발생: {}", e.getMessage());
                    }
                }
            });
        });
    }

    private Group modifyScheduleNotifications(Group group, NotificationType notificationType) {
        return notificationType == NotificationType.START ?
                schedulingService.modifyScheduleStartNotifications(group) :
                schedulingService.modifyScheduleDoneNotifications(group);
    }
}
