package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
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
        UserJpaEntity userJpaEntity = UserJpaEntity.builder().
                userEmail(user.getUserEmail()).
                userRole(user.getUserType().name()).
                userNickname(user.getUserNickname()).
                password(user.getPassword().password()).build();
        return new User.UserId(userJpaRepository.save(userJpaEntity).getUserId());
    }

    @Override
    public Routine.RoutineId saveUserRoutine(Routine routine, User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .userEmail(user.getUserEmail())
                .userRole(user.getUserType().name())
                .userNickname(user.getUserNickname())
                .password(user.getPassword().password())
                .build();
        UserRoutineJpaEntity userRoutineJpaEntity = UserRoutineJpaEntity.builder()
                .routineName(routine.getRoutineName())
                .routineTime(routine.getRoutineTime())
                .index(routine.getIndex())
                .user(userJpaEntity)
                .build();
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
        return userJpaRepository.checkByUserId(user.getId().value());
    }

    @Override
    public User readUserById(User.UserId userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findByUserId(userId.value());
        return User.withId(new User.UserId(userJpaEntity.getUserId()),
                userJpaEntity.getUserNickname(),
                userJpaEntity.getUserEmail(),
                userJpaEntity.getUserType(),
                new User.Password(userJpaEntity.getPassword()),
                userJpaEntity.getUserDeviceToken());
    }

    @Override
    public RoutineWindow readRoutineByUserId(User.UserId userId) {
        List<UserRoutineJpaEntity> routines = userRoutineJpaRepository.readAllByUserUserId(userId.value());
        List<Routine> routineList = routines.stream()
                .map(routine -> Routine.withId(
                        new Routine.RoutineId(routine.getRoutineId()),
                        routine.getRoutineTime(),
                        routine.getRoutineName(),
                        routine.getIndex()
                ))
                .toList();
        return new RoutineWindow(routineList);
    }

    @Override
    public Routine readUserRoutine(Routine.RoutineId routineId) {
        UserRoutineJpaEntity routine = userRoutineJpaRepository.findByRoutineId(routineId.value());
        return Routine.withId(new Routine.RoutineId(routine.getRoutineId()),
                routine.getRoutineTime(),
                routine.getRoutineName(),
                routine.getIndex());
    }

    @Override
    public void removeUser(User user) {
        userJpaRepository.deleteById(user.getId().value());
    }

    @Override
    public void removeUserRoutine(Routine routine) {
        userRoutineJpaRepository.deleteById(routine.getRoutineId().value());
    }
}
