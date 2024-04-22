package com.example.group.mapper;


import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.group.dto.GroupMemberResponse;
import com.example.group.dto.GroupScheduleRequest;
import com.example.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group.GroupId mapToGroupId(Long groupId) {
        return new Group.GroupId(groupId);
    }

    public GroupMemberResponse mapToGroupMemberResponse(List<User> users) {
        List<GroupMemberResponse.GetGroup> getGroups = users.stream()
                .map(user -> new GroupMemberResponse.GetGroup(user.getId().getValue(), user.getUserNickname()))
                .collect(Collectors.toList());
        return new GroupMemberResponse(getGroups, null);
    }

    public Group mapToCreateGroupSchedule(User.UserId userId, GroupScheduleRequest groupScheduleRequest) {
        return Group.withoutId(userId,
                GroupSchedule.withoutId(groupScheduleRequest.groupScheduleName(),
                        groupScheduleRequest.groupDescription(), groupScheduleRequest.groupScheduleLocation(),
                        groupScheduleRequest.groupScheduleDateTime()));
    }
    public Group mapToUpdateGroupSchedule(User.UserId userId, GroupScheduleRequest groupScheduleRequest) {
        GroupSchedule.GroupScheduleId scheduleId = new GroupSchedule.GroupScheduleId(groupScheduleRequest.groupScheduleId());
        GroupSchedule groupSchedule = GroupSchedule.withId(scheduleId,
                groupScheduleRequest.groupScheduleName(),
                groupScheduleRequest.groupScheduleLocation(),
                groupScheduleRequest.groupDescription(),
                groupScheduleRequest.groupScheduleDateTime());
        return Group.withId(new Group.GroupId(groupScheduleRequest.groupId()),userId, groupSchedule);
    }
}
