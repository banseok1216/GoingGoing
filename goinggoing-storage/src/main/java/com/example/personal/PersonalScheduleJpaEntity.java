package com.example.personal;

import com.example.group.model.Group;
import com.example.group.GroupJpaEntity;
import com.example.personal.model.PersonalSchedule;
import com.example.routine.domain.Routine;
import com.example.routine.domain.RoutineWindow;
import com.example.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "personalschedule", schema = "goinggoing")
public class PersonalScheduleJpaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Personal_Schedule_Id")
    private Long personalScheduleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Schedule_Id")
    private GroupJpaEntity userGroup;

    @Basic
    @Column(name = "Duration")
    private Integer duration;
    @Basic
    @Column(name = "Schedule_Start_Time")
    private LocalDateTime scheduleStartTime;
    @Basic
    @Column(name = "Schedule_Done_Time")
    private LocalDateTime scheduleDoneTime;
    @Basic
    @Column(name = "Schedule_Start")
    private Boolean scheduleStart;
    @Basic
    @Column(name = "Schedule_Done")
    private Boolean scheduleDone;
    @Basic
    @Column(name = "Schedule_Notification_Start")
    private Boolean scheduleNotificationStart;

    @Basic
    @Column(name = "Schedule_Notification_Done")
    private Boolean scheduleNotificationDone;

    @Builder
    public PersonalScheduleJpaEntity(Long personalScheduleId, GroupJpaEntity userGroup, Integer duration, Boolean scheduleDone, LocalDateTime scheduleStartTime, LocalDateTime scheduleDoneTime, Boolean scheduleStart, Boolean scheduleNotificationStart, Boolean scheduleNotificationDone, UserJpaEntity user) {
        this.personalScheduleId = personalScheduleId;
        this.scheduleNotificationStart = scheduleNotificationStart;
        this.scheduleNotificationDone = scheduleNotificationDone;
        this.duration = duration;
        this.userGroup = userGroup;
        this.scheduleDone =scheduleDone;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleDoneTime = scheduleDoneTime;
        this.scheduleStart = scheduleStart;
        this.user = user;
    }

    public static PersonalScheduleJpaEntity ofDomain(PersonalSchedule personalSchedule, Group group){
        return PersonalScheduleJpaEntity.builder()
                .user(UserJpaEntity.ofDomain(personalSchedule.getUser()))
                .userGroup(GroupJpaEntity.builder().groupId(group.getId().value()).build())
                .duration(personalSchedule.getPersonalDuration())
                .scheduleStart(personalSchedule.getPersonalScheduleStatus().start())
                .scheduleDone(personalSchedule.getPersonalScheduleStatus().done())
                .scheduleStartTime(personalSchedule.getPersonalScheduleTime().startTime())
                .scheduleDoneTime(personalSchedule.getPersonalScheduleTime().doneTime())
                .scheduleNotificationStart(personalSchedule.getPersonalScheduleSend().sendStartMessage())
                .scheduleNotificationDone(personalSchedule.getPersonalScheduleSend().sendEndMessage())
                .build();
    }

    public PersonalSchedule toPersonalSchedule(List<PersonalScheduleRoutineJpaEntity> routineJpaEntities){
        List<Routine> routines = routineJpaEntities.stream()
                .map(PersonalScheduleRoutineJpaEntity::toRoutine)
                .toList();
        return PersonalSchedule.withId(
                new PersonalSchedule.PersonalScheduleId(this.personalScheduleId),
                this.duration,
                new PersonalSchedule.PersonalScheduleTime(this.scheduleStartTime, this.scheduleDoneTime),
                new PersonalSchedule.PersonalScheduleStatus(this.scheduleStart, this.scheduleDone),
                new RoutineWindow(routines),
                this.user.toUser(),
                new PersonalSchedule.PersonalScheduleSend(this.scheduleNotificationStart, this.scheduleNotificationDone)
        );
    }
}
