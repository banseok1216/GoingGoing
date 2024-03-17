package com.example.goinggoing.personal.mapstruct;
import com.example.goinggoing.group.dto.GroupScheduleResponseDto;
import com.example.goinggoing.personal.dto.PersonalScheduleResponseDto;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonalScheduleMapper {
    PersonalScheduleMapper INSTANCE = Mappers.getMapper(PersonalScheduleMapper.class);

    default PersonalSchedule toEntity(User user, Integer duration, GroupSchedule groupSchedule, Boolean scheduleDone, Boolean scheduleStart,
                                      LocalDateTime scheduleStartTime, LocalDateTime scheduleDoneTime){
        return PersonalSchedule.builder()
                .user(user)
                .duration(duration)
                .groupSchedule(groupSchedule)
                .scheduleDone(scheduleDone)
                .scheduleStart(scheduleStart)
                .scheduleStartTime(scheduleStartTime)
                .scheduleDoneTime(scheduleDoneTime)
                .scheduleNotificationStart(false)
                .scheduleNotificationDone(false)
                .build();
    }

    default List<GroupScheduleResponseDto.GetSchedule> toResponseScheduleListDto(List<PersonalSchedule> scheduleDtoList){
        return scheduleDtoList.stream()
                .map(personalSchedule -> {
                    GroupScheduleResponseDto.GetSchedule schedule = new GroupScheduleResponseDto.GetSchedule();
                    schedule.setScheduleId(personalSchedule.getGroupSchedule().getScheduleId());
                    schedule.setScheduleLocation(personalSchedule.getGroupSchedule().getScheduleLocation());
                    schedule.setScheduleDone(personalSchedule.getScheduleDone());
                    schedule.setScheduleStart(personalSchedule.getScheduleStart());
                    schedule.setScheduleDateTime(personalSchedule.getGroupSchedule().getScheduleDateTime());
                    schedule.setScheduleStartTime(personalSchedule.getScheduleStartTime());
                    schedule.setScheduleDoneTime(personalSchedule.getScheduleDoneTime());
                    schedule.setDuration(personalSchedule.getDuration());
                    schedule.setScheduleName(personalSchedule.getGroupSchedule().getScheduleName());
                    schedule.setPersonalScheduleId(personalSchedule.getPersonalScheduleId());
                    return schedule;
                })
                .collect(Collectors.toList());
    }
    @Mapping(source = "personalScheduleRoutineList", target = "scheduleRoutineDtoList")
    @Mapping(source = "groupSchedule.scheduleId", target = "scheduleId")
    PersonalScheduleResponseDto.GetPersonalSchedule toPersonalScheduleResponseDto(PersonalSchedule personalSchedule);
}
