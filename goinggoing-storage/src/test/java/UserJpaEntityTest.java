import com.example.user.UserJpaEntity;
import com.example.user.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserJpaEntityTest {
    @Test
    public void testOfDomain() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(new User.UserId(1L));
        when(user.getUserNickname()).thenReturn("nickname");
        when(user.getUserEmail()).thenReturn("email@example.com");
        when(user.getPassword()).thenReturn(new User.Password("password"));
        UserJpaEntity result = UserJpaEntity.ofDomain(user);
        assertEquals(1L, result.getUserId());
        assertEquals("nickname", result.getUserNickname());
        assertEquals("email@example.com", result.getUserEmail());
        assertEquals("password", result.getPassword());
    }
    @Test
    public void testToUser() {
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
                .userId(1L)
                .userNickname("nickname")
                .userEmail("email@example.com")
                .password("password")
                .userType(User.UserType.OAUTH_DEFAULT)
                .userDeviceToken("deviceToken")
                .build();
        User user = userJpaEntity.toUser();
        assertEquals(1L, user.getId().value());
    }
}
