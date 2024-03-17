package com.example.goinggoingdomain.domain.personal;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "routineschedule", schema = "goinggoing")
public class PersonalScheduleRoutine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Schedule_Routine_Id")
    private Long scheduleRoutineId;

    @Basic
    @Column(name = "Schedule_Routine_Name")
    private String scheduleRoutineName;

    @Basic
    @Column(name = "Schedule_Routine_Time")
    private Long scheduleRoutineTime;
    @Basic
    @Column(name = "Schedule_Routine_Index")
    private Integer scheduleRoutineIndex;


    @Column(name = "personal_schedule_id")
    private Long personalScheduleId;

    @Builder
    public PersonalScheduleRoutine(Long scheduleRoutineId, String scheduleRoutineName, Long scheduleRoutineTime, Long personalScheduleId, Integer scheduleRoutineIndex, boolean scheduleRoutineDone) {
        this.scheduleRoutineId = scheduleRoutineId;
        this.scheduleRoutineName = scheduleRoutineName;
        this.scheduleRoutineTime = scheduleRoutineTime;
        this.personalScheduleId = personalScheduleId;
        this.scheduleRoutineIndex = scheduleRoutineIndex;
    }

    public void updateScheduleRoutineInfo(PersonalScheduleRoutine updatePersonalScheduleRoutine){
        this.scheduleRoutineName = updatePersonalScheduleRoutine.getScheduleRoutineName();
        this.scheduleRoutineIndex = updatePersonalScheduleRoutine.getScheduleRoutineIndex();
        this.scheduleRoutineTime = updatePersonalScheduleRoutine.getScheduleRoutineTime();
    }
}
