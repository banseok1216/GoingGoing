package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoutineJpaRepository extends JpaRepository<UserRoutineJpaEntity, Long> {
    @Modifying
    @Query("UPDATE UserRoutineJpaEntity ur " +
            "SET ur.index = :index " +
            "WHERE ur.routineId = : routineId")
    void updateUserRoutineIndex(
            @Param("routineId") Long routineId,
            @Param("index") Integer index
    );
    List<UserRoutineJpaEntity> readAllByUserUserId(Long userId);
    UserRoutineJpaEntity findByRoutineId(Long routineId);
}
