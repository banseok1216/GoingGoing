package com.example.personal;

import com.example.personal.model.PersonalSchedule;
import com.example.personal.repository.PersonalRepository;
import com.example.routine.model.Routine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalEntityRepository implements PersonalRepository {
    private final PersonalScheduleJpaRepository personalScheduleJpaRepository;
    private final PersonalScheduleRoutineJpaRepository personalScheduleRoutineJpaRepository;

    public PersonalEntityRepository(PersonalScheduleJpaRepository personalScheduleJpaRepository, PersonalScheduleRoutineJpaRepository personalScheduleRoutineJpaRepository) {
        this.personalScheduleJpaRepository = personalScheduleJpaRepository;
        this.personalScheduleRoutineJpaRepository = personalScheduleRoutineJpaRepository;
    }

    @Override
    public void modifyRoutineOrder(PersonalSchedule personalSchedule) {
        List<PersonalScheduleRoutineJpaEntity> jpaEntities = PersonalScheduleRoutineJpaEntity.ofDomain(personalSchedule);
        jpaEntities.forEach(routine -> personalScheduleRoutineJpaRepository.updatePersonalScheduleOrder(
                routine.getPersonalScheduleJpaEntity().getPersonalScheduleId(),
                routine.getScheduleRoutineId(), routine.getScheduleRoutineIndex())
        );
    }

    @Override
    public void saveRoutine(PersonalSchedule personalSchedule) {
        List<PersonalScheduleRoutineJpaEntity> jpaEntities = PersonalScheduleRoutineJpaEntity.ofDomain(personalSchedule);
        personalScheduleRoutineJpaRepository.saveAll(jpaEntities);
    }

    @Override
    public void modifyPersonalSchedule(PersonalSchedule personalSchedule) {
        personalScheduleJpaRepository.updatePersonalSchedule(
                personalSchedule.getId().value(),
                personalSchedule.getDuration(),
                personalSchedule.getScheduleTime().startTime(),
                personalSchedule.getScheduleTime().doneTime(),
                personalSchedule.getStatus().start(),
                personalSchedule.getStatus().done(),
                personalSchedule.getSend().sendStartMessage(),
                personalSchedule.getSend().sendEndMessage()
        );
    }

    @Override
    public void removePersonalSchedule(PersonalSchedule personalSchedule) {
        personalScheduleJpaRepository.deleteByPersonalScheduleId(personalSchedule.getId().value());
    }

    @Override
    public PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId) {
        List<PersonalScheduleRoutineJpaEntity> personalScheduleJpaEntities = personalScheduleRoutineJpaRepository.findAllByPersonalScheduleJpaEntityPersonalScheduleId(personalScheduleId.value());
        PersonalScheduleJpaEntity personalScheduleJpaEntity = personalScheduleJpaRepository.findByPersonalScheduleId(personalScheduleId.value());
        return personalScheduleJpaEntity.toPersonalScheduleWithRoutine(personalScheduleJpaEntities);
    }


    @Override
    public Routine readRoutine(Routine.RoutineId routineId) {
        PersonalScheduleRoutineJpaEntity jpaEntity = personalScheduleRoutineJpaRepository.findByScheduleRoutineId(routineId.value());
        return jpaEntity.toRoutine();
    }

    @Override
    public void removeRoutine(Routine routine) {
        personalScheduleRoutineJpaRepository.deleteByScheduleRoutineId(routine.getRoutineId().value());
    }
}
