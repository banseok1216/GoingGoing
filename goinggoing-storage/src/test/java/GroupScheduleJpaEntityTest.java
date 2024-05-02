import com.example.group.GroupJpaEntity;
import com.example.group.GroupScheduleJpaEntity;
import com.example.group.model.GroupSchedule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupScheduleJpaEntityTest {
    @Test
    public void testOfDomain() {
        LocalDateTime now = LocalDateTime.now();
        GroupSchedule groupSchedule = GroupSchedule.withoutId("이름", "설명", "장소", now);
        GroupJpaEntity groupJpaEntity = GroupJpaEntity.builder().groupId(1L).build();

        GroupScheduleJpaEntity groupScheduleJpaEntity = GroupScheduleJpaEntity.ofDomain(groupSchedule, groupJpaEntity);

        assertEquals("이름", groupScheduleJpaEntity.getScheduleName());
        assertEquals("설명", groupScheduleJpaEntity.getScheduleDescription());
        assertEquals("장소", groupScheduleJpaEntity.getScheduleLocation());
        assertEquals(now, groupScheduleJpaEntity.getScheduleDateTime());
        assertEquals(1L, groupScheduleJpaEntity.getUserGroup().getGroupId());
    }

    @Test
    public void testToGroupSchedule() {
        LocalDateTime now = LocalDateTime.now();
        GroupScheduleJpaEntity groupScheduleJpaEntity = GroupScheduleJpaEntity.builder()
                .scheduleId(1L)
                .scheduleName("일정")
                .scheduleDescription("설명")
                .scheduleLocation("위치")
                .scheduleDateTime(now)
                .userGroup(GroupJpaEntity.builder().groupId(1L).build())
                .build();

        GroupSchedule groupSchedule = groupScheduleJpaEntity.toGroupSchedule();

        assertEquals(1L, groupSchedule.getId().value());
        assertEquals("일정", groupSchedule.getName());
        assertEquals("설명", groupSchedule.getDescription());
        assertEquals("위치", groupSchedule.getLocation());
        assertEquals(now, groupSchedule.getDate());
    }
}
