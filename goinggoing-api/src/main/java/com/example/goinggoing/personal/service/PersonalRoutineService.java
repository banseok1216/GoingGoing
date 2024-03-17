package com.example.goinggoing.personal.service;

import com.example.goinggoing.personal.dto.PersonalRoutineRequestDto;
import com.example.goinggoing.personal.dto.PersonalRoutineDetailRequestDto;
import com.example.goinggoing.personal.mapstruct.PersonalRoutineDetailMapper;
import com.example.goinggoing.personal.mapstruct.PersonalRoutineMapper;
import com.example.goinggoing.redis.pub.RedisPublisher;
import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
import com.example.goinggoingdomain.domain.personal.repository.PersonalRoutineDetailRepository;
import com.example.goinggoingdomain.domain.personal.repository.PersonalRoutineRepository;
import com.example.goinggoingdomain.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service

public class PersonalRoutineService {
    PersonalRoutineRepository personalRoutineRepository;
    PersonalRoutineDetailRepository personalRoutineDetailRepository;
    RedisPublisher redisPublisher;

    public PersonalRoutineService(PersonalRoutineRepository personalRoutineRepository, PersonalRoutineDetailRepository personalRoutineDetailRepository,RedisPublisher redisPublisher) {
        this.personalRoutineRepository = personalRoutineRepository;
        this.personalRoutineDetailRepository = personalRoutineDetailRepository;
        this.redisPublisher = redisPublisher;
    }

    public List<PersonalRoutine> getAllUserRoutineInfo(Long userId) {
        return personalRoutineRepository.findAllByUserUserIdWithUserRoutineDetail(userId);
    }
    
    @Transactional
    public void deletePersonalRoutineInfo(Long userRoutineId) {
        PersonalRoutine savedPersonalRoutine = personalRoutineRepository.findByUserRoutineId(userRoutineId);
        personalRoutineRepository.deleteByUserRoutineId(savedPersonalRoutine.getUserRoutineId());
    }

    @Transactional
    public void deletePersonalRoutineDetailInfo(Long userRoutineDetailId) {
        PersonalUserRoutineDetail savedPersonalUserRoutineDetail = personalRoutineDetailRepository.findByRoutineDetailId(userRoutineDetailId);
        personalRoutineDetailRepository.deleteByRoutineDetailId(savedPersonalUserRoutineDetail.getRoutineDetailId());
    }

    @Async
    public void createPersonalRoutineInfo(PersonalRoutineRequestDto userRoutineDto, User user) {
        PersonalRoutine personalRoutine = PersonalRoutineMapper.INSTANCE.toOnlyUserRoutineEntity(userRoutineDto.getUserRoutineName(), user);
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
