package com.example.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "userroutinedetail", schema = "goinggoing")
public class UserRoutineDetailJpaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Routine_Detail_Id")
    private Long routineDetailId;

    @Basic
    @Column(name = "Routine_Detail_Time")
    private Long routineDetailTime;

    @Basic
    @Column(name = "Routine_Detail_Name")
    private String routineDetailName;
    @Basic
    @Column(name = "Routine_Detail_Index")
    private Integer index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Routine_Id")
    private UserRoutineJpaEntity personalRoutine;
    @Builder
    public UserRoutineDetailJpaEntity(Long routineDetailId, Long routineDetailTime, String routineDetailName, UserRoutineJpaEntity personalRoutine, Integer index) {
        this.routineDetailId = routineDetailId;
        this.routineDetailName= routineDetailName;
        this.routineDetailTime= routineDetailTime;
        this.personalRoutine = personalRoutine;
        this.index = index;
        personalRoutine.getPersonalUserRoutineDetails().add(this);
    }

}
