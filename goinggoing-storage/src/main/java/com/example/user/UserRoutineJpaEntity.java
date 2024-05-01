package com.example.user;

import com.example.routine.domain.Routine;
import com.example.user.domain.User;
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

    @ManyToOne(targetEntity =UserJpaEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private UserJpaEntity user;

    @Builder
    public UserRoutineJpaEntity(Long routineId, Long routineTime, String routineName, Integer index,UserJpaEntity user) {
        this.routineId = routineId;
        this.routineTime = routineTime;
        this.routineName = routineName;
        this.index = index;
        this.user = user;
    }
    public static UserRoutineJpaEntity ofDomain(Routine routine, User user) {
        return UserRoutineJpaEntity.builder()
                .routineName(routine.getRoutineName())
                .routineTime(routine.getRoutineTime())
                .index(routine.getIndex())
                .user(UserJpaEntity.ofDomain(user))
                .build();
    }
    public Routine toRoutine() {
        return Routine.withId(new Routine.RoutineId(this.routineId),this.routineTime,this.routineName,this.index);
    }
}
