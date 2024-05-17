import com.example.personal.PersonalScheduleJpaEntity;
import com.example.routine.model.RoutineWindow;
import com.example.user.UserJpaEntity;
import com.example.user.model.User;
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
                new PersonalSchedule.Time(LocalDateTime.of(2024, 5, 2, 10, 0), LocalDateTime.of(2024, 5, 2, 11, 0)),
                new PersonalSchedule.Status(true, false),
                new RoutineWindow(new ArrayList<>()),
                user,
                new PersonalSchedule.Send(true, false)
        );
        Group group = Group.withId(new Group.GroupId(1L), null, null);
        PersonalScheduleJpaEntity entity = PersonalScheduleJpaEntity.ofDomain(personalSchedule, group);
        assertEquals(personalSchedule.getDuration(), entity.getDuration());
        assertEquals(personalSchedule.getScheduleTime().startTime(), entity.getScheduleStartTime());
        assertEquals(personalSchedule.getScheduleTime().doneTime(), entity.getScheduleDoneTime());
        assertEquals(personalSchedule.getStatus().start(), entity.getScheduleStart());
        assertEquals(personalSchedule.getStatus().done(), entity.getScheduleDone());
        assertEquals(personalSchedule.getSend().sendStartMessage(), entity.getScheduleNotificationStart());
        assertEquals(personalSchedule.getSend().sendEndMessage(), entity.getScheduleNotificationDone());
        assertEquals(group.getId().value(), entity.getUserGroup().getGroupId());
        assertEquals(UserJpaEntity.ofDomain(user).getUserId(),entity.getUser().getUserId());
    }
    User createUser() {
        return User.withId(new User.UserId(123L), "testNickName", "testEmail", null, new User.Password("123"), "testDeviceToken");
    }
}