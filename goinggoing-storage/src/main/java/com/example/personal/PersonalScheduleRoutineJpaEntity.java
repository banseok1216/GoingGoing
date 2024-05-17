package com.example.personal;

import com.example.personal.model.PersonalSchedule;
import com.example.routine.model.Routine;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "routineschedule", schema = "goinggoing")
public class PersonalScheduleRoutineJpaEntity {
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

    @ManyToOne(targetEntity = PersonalScheduleJpaEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "Personal_Schedule_Id")
    private PersonalScheduleJpaEntity personalScheduleJpaEntity;
    @Builder
    public PersonalScheduleRoutineJpaEntity(Long scheduleRoutineId, String scheduleRoutineName, Long scheduleRoutineTime, Integer scheduleRoutineIndex,PersonalScheduleJpaEntity personalScheduleJpaEntity) {
        this.scheduleRoutineId = scheduleRoutineId;
        this.scheduleRoutineName = scheduleRoutineName;
        this.scheduleRoutineTime = scheduleRoutineTime;
        this.scheduleRoutineIndex = scheduleRoutineIndex;
        this.personalScheduleJpaEntity =personalScheduleJpaEntity;
    }

    public Routine toRoutine(){
        return Routine.withId(new Routine.RoutineId(this.scheduleRoutineId),
                this.scheduleRoutineTime,
                this.scheduleRoutineName,
                this.scheduleRoutineIndex);
    }
    public static List<PersonalScheduleRoutineJpaEntity> ofDomain(PersonalSchedule personalSchedule) {
        return personalSchedule.getRoutineWindow().getRoutines().stream()
                .map(routine -> PersonalScheduleRoutineJpaEntity.builder()
                        .scheduleRoutineId(personalSchedule.getId().value())
                        .scheduleRoutineIndex(routine.getIndex())
                        .scheduleRoutineTime(routine.getRoutineTime())
                        .scheduleRoutineName(routine.getRoutineName())
                        .personalScheduleJpaEntity(PersonalScheduleJpaEntity.builder().personalScheduleId(personalSchedule.getId().value()).build())
                        .build())
                .collect(Collectors.toList());
    }
}
