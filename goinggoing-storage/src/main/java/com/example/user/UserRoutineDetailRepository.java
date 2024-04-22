package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRoutineDetailRepository extends JpaRepository<UserRoutineDetailJpaEntity, Long> {
    @Modifying
    void deleteByRoutineDetailId(Long routineDetailId);

    UserRoutineDetailJpaEntity findByRoutineDetailId(Long routineDetailId);

}
