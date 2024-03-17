package com.example.goinggoingdomain.domain.personal;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "userroutinedetail", schema = "goinggoing")
public class PersonalUserRoutineDetail {
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
    private PersonalRoutine personalRoutine;
    @Builder
    public PersonalUserRoutineDetail(Long routineDetailId, Long routineDetailTime, String routineDetailName, PersonalRoutine personalRoutine, Integer index) {
        this.routineDetailId = routineDetailId;
        this.routineDetailName= routineDetailName;
        this.routineDetailTime= routineDetailTime;
        this.personalRoutine = personalRoutine;
        this.index = index;
        personalRoutine.getPersonalUserRoutineDetails().add(this);
    }

}
