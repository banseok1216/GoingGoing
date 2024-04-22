package com.example.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "userroutine", schema = "goinggoing")
public class UserRoutineJpaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "User_Routine_Id")
    private Long userRoutineId;

    @Basic
    @Column(name = "User_Routine_Name")
    private String userRoutineName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private UserJpaEntity user;

    @OneToMany(mappedBy = "personalRoutine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRoutineDetailJpaEntity> personalUserRoutineDetails = new ArrayList<>();

    @Builder
    public UserRoutineJpaEntity(Long userRoutineId, String userRoutineName, UserJpaEntity user) {
        this.userRoutineId = userRoutineId;
        this.userRoutineName = userRoutineName;
        this.user = user;
    }
}
