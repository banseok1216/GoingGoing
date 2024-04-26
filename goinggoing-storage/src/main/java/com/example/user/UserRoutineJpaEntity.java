package com.example.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "userroutine", schema = "goinggoing")
public class UserRoutineJpaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Routine_Id")
    private Long routineId;

    @Basic
    @Column(name = "Routine_Time")
    private Long routineTime;

    @Basic
    @Column(name = "Routine_Name")
    private String routineName;
    @Basic
    @Column(name = "Routine_Index")
    private Integer index;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @Builder
    public UserRoutineJpaEntity(Long routineId, Long routineTime, String routineName, Integer index,UserJpaEntity user) {
        this.routineId = routineId;
        this.routineTime = routineTime;
        this.routineName = routineName;
        this.index = index;
        this.user = user;
    }

}
