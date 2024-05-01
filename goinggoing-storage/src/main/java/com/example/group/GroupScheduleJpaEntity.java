package com.example.group;

import com.example.group.model.GroupSchedule;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "groupschedule", schema = "goinggoing")
public class GroupScheduleJpaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Schedule_Id")
    private Long scheduleId;
    @Basic
    @Column(name = "Group_Schedule_Name")
    private String scheduleName;
    @Basic
    @Column(name = "Group_Schedule_Date")
    private LocalDateTime scheduleDateTime;
    @Basic
    @Column(name = "Group_Schedule_Location")
    private String scheduleLocation;
    @Basic
    @Column(name = "Group_Schedule_Description")
    private String scheduleDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupJpaEntity userGroup;

    @Builder
    public GroupScheduleJpaEntity(Long scheduleId, String scheduleName, LocalDateTime scheduleDateTime, String scheduleLocation,String scheduleDescription, GroupJpaEntity userGroup) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleDateTime = scheduleDateTime;
        this.scheduleLocation = scheduleLocation;
        this.scheduleDescription =scheduleDescription;
        this.userGroup = userGroup;
    }

    public static GroupScheduleJpaEntity ofDomain(GroupSchedule groupSchedule, GroupJpaEntity groupJpaEntity) {
        return GroupScheduleJpaEntity.builder()
                .scheduleId(groupSchedule.getId().value())
                .scheduleDateTime(groupSchedule.getDate())
                .scheduleLocation(groupSchedule.getLocation())
                .userGroup(groupJpaEntity)
                .scheduleDescription(groupSchedule.getDescription())
                .build();
    }
    public GroupSchedule toGroupSchedule() {
        return GroupSchedule.withId(new GroupSchedule.GroupScheduleId(this.scheduleId),this.scheduleName,this.scheduleDescription,this.scheduleLocation,this.scheduleDateTime);
    }
}
