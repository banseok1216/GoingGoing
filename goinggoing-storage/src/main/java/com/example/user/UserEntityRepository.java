package com.example.user;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserEntityRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserRoutineJpaRepository userRoutineJpaRepository;

    public UserEntityRepository(UserJpaRepository userJpaRepository, UserRoutineJpaRepository userRoutineJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userRoutineJpaRepository = userRoutineJpaRepository;
    }

    @Override
    public User.UserId saveUser(User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.ofDomain(user);
        return new User.UserId(userJpaRepository.save(userJpaEntity).getUserId());
    }

    @Override
    public Routine.RoutineId saveUserRoutine(Routine routine, User user) {
        UserRoutineJpaEntity userRoutineJpaEntity = UserRoutineJpaEntity.ofDomain(routine,user);
        return new Routine.RoutineId(userRoutineJpaRepository.save(userRoutineJpaEntity).getRoutineId());
    }

    @Override
    public void saveUserRoutines(RoutineWindow routineWindow) {
        routineWindow.getRoutines().forEach(routine -> {
            userRoutineJpaRepository.updateUserRoutineIndex(routine.getRoutineId().value(), routine.getIndex());
        });
    }

    @Override
    public boolean check(User user) {
        return userJpaRepository.existsByUserId(user.getId().value());
    }

    @Override
    public User readUserById(User.UserId userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findByUserId(userId.value());
        return userJpaEntity.toUser();
    }

    @Override
    public RoutineWindow readRoutineByUserId(User.UserId userId) {
        List<UserRoutineJpaEntity> routines = userRoutineJpaRepository.readAllByUserUserId(userId.value());
        List<Routine> routineList = routines.stream()
                .map(UserRoutineJpaEntity::toRoutine
                )
                .toList();
        return new RoutineWindow(routineList);
    }

    @Override
    public Routine readUserRoutine(Routine.RoutineId routineId) {
        UserRoutineJpaEntity routine = userRoutineJpaRepository.findByRoutineId(routineId.value());
        return routine.toRoutine();
    }

    @Override
    public void logout(User user) {
        userJpaRepository.deleteById(user.getId().value());
    }

    @Override
    public void removeUserRoutine(Routine routine) {
        userRoutineJpaRepository.deleteById(routine.getRoutineId().value());
    }
}
