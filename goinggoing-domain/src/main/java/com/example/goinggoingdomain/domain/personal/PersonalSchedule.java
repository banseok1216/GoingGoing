package com.example.goinggoingdomain.domain.personal;

import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.user.User;
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
public class PersonalSchedule {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Personal_Schedule_Id")
    private Long personalScheduleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Schedule_Id")
    private GroupSchedule groupSchedule;
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
    private List<PersonalScheduleRoutine> personalScheduleRoutineList = new ArrayList<>();

    @Builder
    public PersonalSchedule(Long personalScheduleId, GroupSchedule groupSchedule, Integer duration, Boolean scheduleDone, LocalDateTime scheduleStartTime, LocalDateTime scheduleDoneTime, Boolean scheduleStart, Boolean scheduleNotificationStart, Boolean scheduleNotificationDone, User user) {
        this.personalScheduleId = personalScheduleId;
        this.scheduleNotificationStart = scheduleNotificationStart;
        this.scheduleNotificationDone = scheduleNotificationDone;
        this.duration = duration;
        this.groupSchedule = groupSchedule;
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
