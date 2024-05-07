package fontys.ind.configuration.security.token.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fontys.ind.configuration.security.token.AccessToken;
import fontys.ind.configuration.security.token.exception.InvalidAccessTokenException;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {

    private AccessTokenEncoderDecoderImpl encoderDecoder;

    @BeforeEach
    void setUp() {
        // Provide a fixed, known good secret key for testing
        encoderDecoder = new AccessTokenEncoderDecoderImpl("testsecretkey12345678901234567890123456789012"); // key length must fit requirements
    }

//    @Test
//    void testEncodeDecode() {
//        AccessTokenImpl originalToken = new AccessTokenImpl("user@example.com", 1L, "ADMIN");
//        String encoded = encoderDecoder.encode(originalToken);
//        AccessToken decodedToken = encoderDecoder.decode(encoded);
//
//        assertEquals(originalToken.getSubject(), decodedToken.getSubject());
//        assertEquals(originalToken.getUserId(), decodedToken.getUserId());
//        assertTrue(decodedToken.hasRole("ADMIN"));
//    }

    @Test
    void testInvalidToken() {
        assertThrows(InvalidAccessTokenException.class, () -> {
            encoderDecoder.decode("invalid.token.here");
        });
    }
}
