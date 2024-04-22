//package com.example.personal.service;
//
//import com.example.personal.dto.PersonalRoutineRequestDto;
//import com.example.personal.dto.PersonalRoutineDetailRequestDto;
//import com.example.redis.pub.RedisPublisher;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//@Service
//
//public class PersonalRoutineService {
//    PersonalRoutineRepository personalRoutineRepository;
//    UserRoutineDetailRepository personalRoutineDetailRepository;
//    RedisPublisher redisPublisher;
//
//    public PersonalRoutineService(PersonalRoutineRepository personalRoutineRepository, UserRoutineDetailRepository personalRoutineDetailRepository,RedisPublisher redisPublisher) {
//        this.personalRoutineRepository = personalRoutineRepository;
//        this.personalRoutineDetailRepository = personalRoutineDetailRepository;
//        this.redisPublisher = redisPublisher;
//    }
//
//    public List<PersonalRoutine> getAllUserRoutineInfo(Long userId) {
//        return personalRoutineRepository.findAllByUserUserIdWithUserRoutineDetail(userId);
//    }
//
//    @Transactional
//    public void deletePersonalRoutineInfo(Long userRoutineId) {
//        PersonalRoutine savedPersonalRoutine = personalRoutineRepository.findByUserRoutineId(userRoutineId);
//        personalRoutineRepository.deleteByUserRoutineId(savedPersonalRoutine.getUserRoutineId());
//    }
//
//    @Transactional
//    public void deletePersonalRoutineDetailInfo(Long userRoutineDetailId) {
//        PersonalUserRoutineDetail savedPersonalUserRoutineDetail = personalRoutineDetailRepository.findByRoutineDetailId(userRoutineDetailId);
//        personalRoutineDetailRepository.deleteByRoutineDetailId(savedPersonalUserRoutineDetail.getRoutineDetailId());
//    }
//
//    @Async
//    public void createPersonalRoutineInfo(PersonalRoutineRequestDto userRoutineDto, User user) {
//        PersonalRoutine personalRoutine = PersonalRoutineMapper.INSTANCE.toOnlyUserRoutineEntity(userRoutineDto.getUserRoutineName(), user);
//        personalRoutine = personalRoutineRepository.save(personalRoutine);
//        List<PersonalRoutineDetailRequestDto> routineDetails = userRoutineDto.getRoutineDetail();
//        List<PersonalUserRoutineDetail> personalUserRoutineDetailEntities = new ArrayList<>();
//        for (PersonalRoutineDetailRequestDto detailDto : routineDetails) {
//            PersonalUserRoutineDetail personalUserRoutineDetail = PersonalRoutineDetailMapper.INSTANCE.toAddEntity(detailDto, personalRoutine);
//            personalUserRoutineDetailEntities.add(personalUserRoutineDetail);
//        }
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(personalUserRoutineDetailEntities.stream()
//                .map(userRoutineDetailEntity -> CompletableFuture.runAsync(() -> personalRoutineDetailRepository.save(userRoutineDetailEntity)))
//                .toArray(CompletableFuture[]::new));
//        allFutures.join();
//    }
//
//}
