package com.example.personal;

import com.example.group.GroupScheduleJpaEntity;
import com.example.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private UserJpaEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Schedule_Id")
    private GroupScheduleJpaEntity groupScheduleJpaEntity;
    @Basic
    @Column(name = "Duration")
    private Integer duration;
    @Basic
    @Column(name = "Schedule_Done")
    private Boolean scheduleDone;
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
    @Column(name = "Schedule_Notification_Start")
    private Boolean scheduleNotificationStart;

    @Basic
    @Column(name = "Schedule_Notification_Done")
    private Boolean scheduleNotificationDone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_schedule_id")
    private List<PersonalScheduleRoutineJpaEntity> personalScheduleRoutineList = new ArrayList<>();

    @Builder
    public PersonalScheduleJpaEntity(Long personalScheduleId, GroupScheduleJpaEntity groupScheduleJpaEntity, Integer duration, Boolean scheduleDone, LocalDateTime scheduleStartTime, LocalDateTime scheduleDoneTime, Boolean scheduleStart, Boolean scheduleNotificationStart, Boolean scheduleNotificationDone, UserJpaEntity user) {
        this.personalScheduleId = personalScheduleId;
        this.scheduleNotificationStart = scheduleNotificationStart;
        this.scheduleNotificationDone = scheduleNotificationDone;
        this.duration = duration;
        this.groupScheduleJpaEntity = groupScheduleJpaEntity;
        this.scheduleDone =scheduleDone;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleDoneTime = scheduleDoneTime;
        this.scheduleStart = scheduleStart;
        this.user = user;
    }
    public void updateInfo(Integer duration, Boolean scheduleDone, LocalDateTime scheduleStartTime, LocalDateTime scheduleDoneTime, Boolean scheduleStart) {
        this.duration = duration;
        this.scheduleDone =scheduleDone;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleDoneTime = scheduleDoneTime;
        this.scheduleStart = scheduleStart;
    }
    public void updateNotificationStart(Boolean scheduleNotificationStart) {
        this.scheduleNotificationStart = scheduleNotificationStart;
    }
    public void updateNotificationDone(Boolean scheduleNotificationDone) {
        this.scheduleNotificationDone = scheduleNotificationDone;
    }
}
