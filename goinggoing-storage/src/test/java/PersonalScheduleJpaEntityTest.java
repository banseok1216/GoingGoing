import com.example.personal.PersonalScheduleJpaEntity;
import com.example.routine.domain.RoutineWindow;
import com.example.user.UserJpaEntity;
import com.example.user.domain.User;
import org.junit.jupiter.api.Test;
import com.example.group.model.Group;
import com.example.personal.model.PersonalSchedule;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalScheduleJpaEntityTest {
    @Test
    public void testOfDomain() {
        User user = createUser();
        PersonalSchedule personalSchedule = PersonalSchedule.withId(
                new PersonalSchedule.PersonalScheduleId(1L),
                30,
                new PersonalSchedule.PersonalScheduleTime(LocalDateTime.of(2024, 5, 2, 10, 0), LocalDateTime.of(2024, 5, 2, 11, 0)),
                new PersonalSchedule.PersonalScheduleStatus(true, false),
                new RoutineWindow(new ArrayList<>()),
                user,
                new PersonalSchedule.PersonalScheduleSend(true, false)
        );
        Group group = Group.withId(new Group.GroupId(1L), null, null);
        PersonalScheduleJpaEntity entity = PersonalScheduleJpaEntity.ofDomain(personalSchedule, group);
        assertEquals(personalSchedule.getPersonalDuration(), entity.getDuration());
        assertEquals(personalSchedule.getPersonalScheduleTime().startTime(), entity.getScheduleStartTime());
        assertEquals(personalSchedule.getPersonalScheduleTime().doneTime(), entity.getScheduleDoneTime());
        assertEquals(personalSchedule.getPersonalScheduleStatus().start(), entity.getScheduleStart());
        assertEquals(personalSchedule.getPersonalScheduleStatus().done(), entity.getScheduleDone());
        assertEquals(personalSchedule.getPersonalScheduleSend().sendStartMessage(), entity.getScheduleNotificationStart());
        assertEquals(personalSchedule.getPersonalScheduleSend().sendEndMessage(), entity.getScheduleNotificationDone());
        assertEquals(group.getId().value(), entity.getUserGroup().getGroupId());
        assertEquals(UserJpaEntity.ofDomain(user).getUserId(),entity.getUser().getUserId());
    }
    User createUser() {
        return User.withId(new User.UserId(123L), "testNickName", "testEmail", null, new User.Password("123"), "testDeviceToken");
    }
}