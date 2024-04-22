package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoutineJpaRepository extends JpaRepository<UserRoutineJpaEntity, Long> {
    @Query("SELECT us FROM UserRoutineJpaEntity us JOIN FETCH us.personalUserRoutineDetails urd WHERE us.user.userId = :userId")
    List<UserRoutineJpaEntity> findAllByUserUserIdWithUserRoutineDetail(@Param("userId") Long userId);
    UserRoutineJpaEntity findByUserRoutineId(Long userRoutineId);
    void deleteByUserRoutineId(Long userRoutineId);
}
