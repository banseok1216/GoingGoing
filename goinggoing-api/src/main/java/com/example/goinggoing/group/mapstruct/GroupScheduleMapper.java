package com.example.goinggoing.group.mapstruct;

import com.example.goinggoing.group.dto.GroupScheduleRequestDto;
import com.example.goinggoing.group.dto.GroupScheduleResponseDto;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupScheduleMapper {
    GroupScheduleMapper INSTANCE = Mappers.getMapper(GroupScheduleMapper.class);

    GroupScheduleResponseDto.CreateScheduleId toResponseScheduleIdDto(Long scheduleId);

    GroupSchedule toEntity(GroupScheduleRequestDto groupScheduleRequestDto);
}
