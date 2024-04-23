package s3.ind.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import s3.ind.business.exception.InvalidCredentialsException;
import s3.ind.configuration.security.token.AccessTokenEncoder;
import s3.ind.domain.request.LoginRequest;
import s3.ind.domain.response.LoginResponse;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.entity.RoleEnum;
import s3.ind.persistence.entity.UserEntity;

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

    private UserEntity user;
    private final String email = "user@example.com";
    private final String password = "securePassword";
    private final String encodedPassword = "encodedSecurePassword";
    private final String accessToken = "access_token";

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setUserId(1);
        user.setRole(RoleEnum.CLIENT);  // Assuming RoleEnum exists
    }

    @Test
    void login_Successful() {
        // Arrange
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn(accessToken);

        LoginRequest loginRequest = new LoginRequest(email, password);

        // Act
        LoginResponse response = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(accessToken, response.getAccessToken());
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(password, encodedPassword);
    }

    @Test
    void login_InvalidCredentials_ThrowsException() {
        // Arrange
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        LoginRequest loginRequest = new LoginRequest(email, password);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(password, encodedPassword);
    }

    @Test
    void login_UserNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findByEmail(email)).thenReturn(null);
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository).findByEmail(email);
    }
}
