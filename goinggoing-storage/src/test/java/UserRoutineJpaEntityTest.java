import com.example.routine.model.Routine;
import com.example.user.UserJpaEntity;
import com.example.user.UserRoutineJpaEntity;
import com.example.user.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserRoutineJpaEntityTest {

    @Test
    public void testOfDomain() {
        Routine routine = Routine.withoutId(100L, "testName", 1);
        User user = User.withId(new User.UserId(1L), "username", "email@example.com", User.UserType.LOCAL, new User.Password("password"), "deviceToken");

        UserRoutineJpaEntity userRoutineJpaEntity = UserRoutineJpaEntity.ofDomain(routine, user);

        assertEquals("testName", userRoutineJpaEntity.getRoutineName());
        assertEquals(100L, userRoutineJpaEntity.getRoutineTime());
        assertEquals(1, userRoutineJpaEntity.getIndex());
        assertEquals(1L, userRoutineJpaEntity.getUser().getUserId());
    }

    @Test
    public void testToRoutine() {
        UserRoutineJpaEntity userRoutineJpaEntity = UserRoutineJpaEntity.builder()
                .routineId(1L)
                .routineTime(100L)
                .routineName("testName")
                .index(1)
                .user(UserJpaEntity.builder().userId(1L).build())
                .build();

        Routine routine = userRoutineJpaEntity.toRoutine();

        assertEquals(1L, routine.getRoutineId().value());
        assertEquals("testName", routine.getRoutineName());
        assertEquals(100L, routine.getRoutineTime());
        assertEquals(1, routine.getIndex());
    }
}