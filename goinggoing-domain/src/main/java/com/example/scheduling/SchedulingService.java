package com.example.scheduling;

import com.example.group.model.Group;
import com.example.group.implementation.GroupReader;
import com.example.notification.model.Notification;
import com.example.notification.implementation.NotificationSender;
import com.example.personal.implementation.PersonalUpdater;
import com.example.personal.model.PersonalSchedule;
import com.example.personal.implementation.PersonalWriter;
import com.example.user.implementation.UserCachedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class SchedulingService {
    private final PersonalUpdater personalUpdater;
    private final GroupReader groupReader;
    private final NotificationSender notificationSender;
    private final UserCachedHandler userCachedHandler;

    public Group modifyScheduleDoneNotifications(Group group) {
        PersonalSchedule personalSchedule = group.getPersonalSchedules().get(0);
        personalSchedule =personalSchedule.updateStatusAndTime();
        personalSchedule =personalSchedule.updateEndNotified();
        personalUpdater.modifyPersonalSchedule(personalSchedule);
        return groupReader.readGroup(group.getId());
    }

    private Group modifyScheduleStartNotifications(Group group) {
        PersonalSchedule personalSchedule = group.getPersonalSchedules().get(0);
        personalSchedule =personalSchedule.updateStatusAndTime();
        personalSchedule = personalSchedule.updateStartNotified();
        personalUpdater.modifyPersonalSchedule(personalSchedule);
        return groupReader.readGroup(group.getId());
    }

    private Group modifyScheduleNotifications(Group group, Notification.Type notificationType) {
        return notificationType == Notification.Type.START ?
                modifyScheduleStartNotifications(group) :
                modifyScheduleDoneNotifications(group);
    }

    @Scheduled(cron = "0 */1 * * * *",zone = "Asia/Seoul")
    @SchedulerLock(name = "startNotifications")
    private void sendScheduleStartNotifications() {
        List<Group> groupList = groupReader.readPersonalSchedulesWithNotStart();
        groupList.forEach(group -> {
                    Group savedGroup = modifyScheduleNotifications(group, Notification.Type.START);
                    sendNotifications(savedGroup, Notification.Type.START);
                }
        );
    }

    @Scheduled(cron = "0 */1 * * * *",zone = "Asia/Seoul")
    @SchedulerLock(name = "doneNotifications")
    private void sendScheduleDoneNotifications() {
        List<Group> groupList = groupReader.readPersonalSchedulesWithNotDone();
        groupList.forEach(group -> {
                    Group savedGroup = modifyScheduleNotifications(group, Notification.Type.DONE);
                    sendNotifications(savedGroup, Notification.Type.DONE);
                }
        );
    }

    @Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
    public void refreshUserCached() {
        userCachedHandler.clear();
    }

    private void sendNotifications(Group group, Notification.Type type) {
        group.getPersonalSchedules().forEach(personalSchedule -> {
            String deviceToken = personalSchedule.getUser().getDeviceToken();
            if (deviceToken != null) {
                Notification notification = new Notification(deviceToken, personalSchedule.getUser(),group.getId().value(), type);
                notificationSender.sendNotification(notification);
            }
        });
    }
}
