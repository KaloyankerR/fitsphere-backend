package s3.ind.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.mappers.ClientMapper;
import s3.ind.business.mappers.UserMapper;
import s3.ind.domain.request.user.CreateUserRequest;
import s3.ind.domain.response.user.CreateUserResponse;
import s3.ind.persistence.ClientRepository;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.entity.ClientEntity;
import s3.ind.persistence.entity.UserEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientEntity clientEntity;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createUser_Successful() {
//        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@example.com", "password123", "CLIENT");
//        UserEntity newUser = new UserEntity();
//        newUser.setUserId(1);
//
//        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
//        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(UserEntity.class))).thenReturn(newUser);
//
//        CreateUserResponse response = userService.createUser(request);
//
//        assertNotNull(response);
//        assertEquals(1L, response.getUserId());
//        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void createUser_EmailAlreadyExists() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@example.com", "password123", "CLIENT");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(request));
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void createUser() {
    }

    @Test
    void createTrainer() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllTrainers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void checkUserPermission() {
    }

}