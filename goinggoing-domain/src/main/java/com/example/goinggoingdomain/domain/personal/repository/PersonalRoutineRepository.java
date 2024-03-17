package com.example.goinggoingdomain.domain.personal.repository;

import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalRoutineRepository extends JpaRepository<PersonalRoutine, Long> {
    @Query("SELECT us FROM PersonalRoutine us JOIN FETCH us.personalUserRoutineDetails urd WHERE us.user.userId = :userId")
    List<PersonalRoutine> findAllByUserUserIdWithUserRoutineDetail(@Param("userId") Long userId);
    PersonalRoutine findByUserRoutineId(Long userRoutineId);
    void deleteByUserRoutineId(Long userRoutineId);
}
