package com.example.user.service;

import com.example.personal.RoutineWindow;
import com.example.redis.pub.RedisPublisher;
import com.example.routine.Routine;
import com.example.user.User;
import com.example.user.UserReader;
import com.example.user.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserRoutineService {
    private final UserReader userReader;
    private final UserWriter userWriter;

    public RoutineWindow getAllUserRoutineInfo(User.UserId userId) {
        return userReader.readUserRoutines(userId);
    }

    @Transactional
    public void deletePersonalRoutineInfo(Routine.RoutineId userRoutineId) {
        userReader.readUserRoutine(userRoutineId);
        us
    }

    @Transactional
    public void deletePersonalRoutineDetailInfo(Long userRoutineDetailId) {
        PersonalUserRoutineDetail savedPersonalUserRoutineDetail = personalRoutineDetailRepository.findByRoutineDetailId(userRoutineDetailId);
        personalRoutineDetailRepository.deleteByRoutineDetailId(savedPersonalUserRoutineDetail.getRoutineDetailId());
    }

    @Async
    public void createPersonalRoutineInfo(PersonalRoutineRequestDto userRoutineDto, User user) {
        ScheduleRoutine personalRoutine = PersonalRoutineMapper.INSTANCE.toOnlyUserRoutineEntity(userRoutineDto.getUserRoutineName(), user);
        personalRoutine = personalRoutineRepository.save(personalRoutine);
        List<PersonalRoutineDetailRequestDto> routineDetails = userRoutineDto.getRoutineDetail();
        List<PersonalUserRoutineDetail> personalUserRoutineDetailEntities = new ArrayList<>();
        for (PersonalRoutineDetailRequestDto detailDto : routineDetails) {
            PersonalUserRoutineDetail personalUserRoutineDetail = PersonalRoutineDetailMapper.INSTANCE.toAddEntity(detailDto, personalRoutine);
            personalUserRoutineDetailEntities.add(personalUserRoutineDetail);
        }
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(personalUserRoutineDetailEntities.stream()
                .map(userRoutineDetailEntity -> CompletableFuture.runAsync(() -> personalRoutineDetailRepository.save(userRoutineDetailEntity)))
                .toArray(CompletableFuture[]::new));
        allFutures.join();
    }

}
