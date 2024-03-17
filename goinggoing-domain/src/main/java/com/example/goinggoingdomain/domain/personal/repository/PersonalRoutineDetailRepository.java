package com.example.goinggoingdomain.domain.personal.repository;

import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PersonalRoutineDetailRepository extends JpaRepository<PersonalUserRoutineDetail, Long> {
    @Modifying
    void deleteByRoutineDetailId(Long routineDetailId);

    PersonalUserRoutineDetail findByRoutineDetailId(Long routineDetailId);

}
