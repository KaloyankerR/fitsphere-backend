package fontys.ind.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import s3.ind.business.exception.InvalidCredentialsException;
import fontys.ind.configuration.security.token.AccessTokenEncoder;
import fontys.ind.domain.request.LoginRequest;
import fontys.ind.domain.response.LoginResponse;
import fontys.ind.persistence.UserRepository;
import fontys.ind.domain.RoleEnum;
import fontys.ind.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    private static final String EMAIL = "user@example.com";
    private static final String PASSWORD = "securePassword";
    private static final String ENCODED_PASSWORD = "encodedSecurePassword";
    private static final String ACCESS_TOKEN = "access_token";

    private UserEntity user;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setEmail(EMAIL);
        user.setPassword(ENCODED_PASSWORD);
        user.setUserId(1);
        user.setRole(RoleEnum.CLIENT);  // Assuming RoleEnum exists

        loginRequest = new LoginRequest(EMAIL, PASSWORD);
    }

    @Test
    void whenLoginIsSuccessful_thenReturnsAccessToken() {
        // Arrange
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        when(passwordEncoder.matches(PASSWORD, ENCODED_PASSWORD)).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn(ACCESS_TOKEN);

        // Act
        LoginResponse response = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(ACCESS_TOKEN, response.getAccessToken());
        verify(userRepository).findByEmail(EMAIL);
        verify(passwordEncoder).matches(PASSWORD, ENCODED_PASSWORD);
    }

    @Test
    void whenCredentialsAreInvalid_thenThrowsInvalidCredentialsException() {
        // Arrange
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        when(passwordEncoder.matches(PASSWORD, ENCODED_PASSWORD)).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository).findByEmail(EMAIL);
        verify(passwordEncoder).matches(PASSWORD, ENCODED_PASSWORD);
    }

    @Test
    void whenUserNotFound_thenThrowsInvalidCredentialsException() {
        // Arrange
        when(userRepository.findByEmail(EMAIL)).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository).findByEmail(EMAIL);
    }
}
