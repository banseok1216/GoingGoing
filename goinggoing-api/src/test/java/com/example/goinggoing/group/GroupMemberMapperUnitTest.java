//package com.example.goinggoing.group;
//
//import com.example.group.dto.GroupMemberResponseDto;
//import com.example.group.dto.GroupMemeberDto;
//import com.example.goinggoing.group.mapper.GroupMemberMapper;
//import com.example.goinggoingdomain.domain.group.GroupSchedule;
//import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
//import com.example.goinggoingdomain.domain.controller.User;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mapper.factory.Mappers;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class GroupMemberMapperUnitTest {
//
//    private final GroupMemberMapper mapper = Mappers.getMapper(GroupMemberMapper.class);
//
//    @Test
//    @DisplayName("GroupDto로 바꾸는 테스트")
//    public void testToGroupDto() {
//        // Arrange
//        Long userId = 1L;
//        Long scheduleId = 2L;
//
//        // Act
//        GroupMemeberDto groupMemeberDto = mapper.toGroupDto(userId, scheduleId);
//
//        // Assert
//        assertEquals(userId, groupMemeberDto.getUserId());
//        assertEquals(scheduleId, groupMemeberDto.getScheduleId());
//    }
//
//    @Test
//    @DisplayName("GetResponseDto로 바꾸는 테스트")
//    public void testToGetGroupResponseDto() {
//        User user1 = User.builder().userId(1L).build();
//        User user2 = User.builder().userId(2L).build();
//        GroupMemberResponseDto.GetGroup result = mapper.toGetGroupResponseDto(List.of(user1, user2)).get(0);
//        assertEquals(user1.getUserId(), result.getUserId());
//    }
//
//    @Test
//    @DisplayName("AddResponseDto로 바꾸는 테스트")
//    public void testToAddGroupResponseDto() {
//        Long personalScheduleId = 1L;
//        GroupMemberResponseDto.AddGroup result = mapper.toAddGroupResponseDto(personalScheduleId);
//        assertEquals(personalScheduleId, result.getPersonalScheduleId());
//    }
//    @Test
//    @DisplayName("Personal엔티티로 바꾸는 테스트")
//    public void testToEntity() {
//        User controller = User.builder().userId(1L).build();
//        GroupSchedule groupSchedule = GroupSchedule.builder().scheduleId(2L).build();
//        PersonalSchedule result = mapper.toEntity(controller, groupSchedule);
//        assertEquals(controller.getUserId(), result.getUser().getUserId());
//        assertEquals(groupSchedule.getScheduleId(), result.getGroupSchedule().getScheduleId());
//    }
//}
