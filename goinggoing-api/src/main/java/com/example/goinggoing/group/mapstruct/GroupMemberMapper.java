package com.example.goinggoing.group.mapstruct;

import com.example.goinggoing.group.dto.GroupMemberResponseDto;
import com.example.goinggoing.group.dto.GroupMemeberDto;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GroupMemberMapper {
    GroupMemberMapper INSTANCE = Mappers.getMapper(GroupMemberMapper.class);
    GroupMemeberDto toGroupDto(Long userId, Long scheduleId);
    List<GroupMemberResponseDto.GetGroup> toGetGroupResponseDto(List<User> userList);
    GroupMemberResponseDto.AddGroup toAddGroupResponseDto(Long personalScheduleId);
    PersonalSchedule toEntity(User user, GroupSchedule groupSchedule);
}
