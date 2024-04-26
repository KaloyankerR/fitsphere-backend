package s3.ind.configuration.security.token.impl;

import org.junit.jupiter.api.Test;

// import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenImplTest {

    @Test
    void testRoleHandling() {
        AccessTokenImpl tokenWithRole = new AccessTokenImpl("user@example.com", 1L, "ADMIN");
        assertTrue(tokenWithRole.hasRole("ADMIN"));
        assertFalse(tokenWithRole.hasRole("TRAINER"));

        AccessTokenImpl tokenNoRole = new AccessTokenImpl("user@example.com", 1L, null);
        assertFalse(tokenNoRole.hasRole("CLIENT"));
    }
}
