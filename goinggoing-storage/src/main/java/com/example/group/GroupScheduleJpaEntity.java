package com.example.group;

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

    @Builder
    public GroupScheduleJpaEntity(Long scheduleId, String scheduleName, LocalDateTime scheduleDateTime, String scheduleLocation) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleDateTime = scheduleDateTime;
        this.scheduleLocation = scheduleLocation;
    }

    public void updateScheduleNameTimeLocation(
            LocalDateTime newDateTime, String newLocation, String newName) {
        this.scheduleDateTime = newDateTime;
        this.scheduleLocation = newLocation;
        this.scheduleName = newName;
    }
}
