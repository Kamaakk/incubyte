import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testConstructor_shouldCreateUserObject() {
        String userId = "user123";
        String name = "John Doe";
        String email = "john.doe@example.com";
        boolean isAdmin = false; // Or appropriate default for isAdmin

        User user = new User(userId, name, email, isAdmin);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(isAdmin, user.isAdmin());
    }

    @Test
    public void testSetter_shouldUpdateUserProperties() {
        User user = new User("user123", "John Doe", "john.doe@example.com", false);

        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setIsAdmin(true);

        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertTrue(user.isAdmin());
    }
}
