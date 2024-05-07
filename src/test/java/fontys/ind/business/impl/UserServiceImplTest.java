package fontys.ind.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import s3.ind.business.exception.EmailAlreadyExistsException;
import fontys.ind.business.mappers.ClientMapper;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.request.user.UpdateUserRequest;
import fontys.ind.domain.response.user.*;
import fontys.ind.persistence.ClientRepository;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.UserRepository;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.UserEntity;
import fontys.ind.business.mappers.UserMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createUser_WhenEmailExists_ThrowsException() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("user@example.com");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(request));
    }

//    @Test
//    void createUser_WhenNewUser_CreatesSuccessfully() {
//        CreateUserRequest request = new CreateUserRequest();
//        request.setEmail("newuser@example.com");
//        request.setPassword("password");
//        request.setRole("CLIENT");
//
//        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
//        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
//        when(clientRepository.save(any())).thenReturn(new UserEntity());
//
//        CreateUserResponse response = userService.createUser(request);
//
//        assertNotNull(response);
//        verify(userRepository, never()).save(any(UserEntity.class)); // As clients are handled separately
//        verify(clientRepository).save(any(ClientEntity.class));
//    }

    @Test
    void updateUser_WhenUserNotFound_ThrowsException() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(1L);
        when(userRepository.findById(request.getUserId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUser(request));
    }

    @Test
    void updateUser_WhenFound_UpdatesSuccessfully() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(1L);
        request.setFirstName("Updated Name");

        UserEntity user = new UserEntity();
        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(user));

        userService.updateUser(request);

        verify(userRepository).save(user);
        assertEquals("Updated Name", user.getFirstName());
    }

    @Test
    void getAllUsers_ReturnsUserList() {
        when(userRepository.findAll()).thenReturn(List.of(new UserEntity()));
        when(userMapper.fromEntityToResponse(any(UserEntity.class))).thenReturn(new GetUserResponse());

        GetAllUsersResponse response = userService.getAllUsers();

        assertNotNull(response);
        assertFalse(response.getUsers().isEmpty());
        verify(userRepository).findAll();
        verify(userMapper).fromEntityToResponse(any(UserEntity.class));
    }

//    @Test
//    void getAllTrainers_ReturnsAllTrainers() {
//        // Arrange
//        TrainerEntity trainer1 = new TrainerEntity();
//        TrainerEntity trainer2 = new TrainerEntity();
//        List<TrainerEntity> trainerList = Arrays.asList(trainer1, trainer2);
//        GetTrainerResponse trainerResponse1 = new GetTrainerResponse();
//        GetTrainerResponse trainerResponse2 = new GetTrainerResponse();
//
//        when(trainerRepository.findAll()).thenReturn(trainerList);
//        when(trainerMapper.fromEntityToResponse(trainer1)).thenReturn(trainerResponse1);
//        when(trainerMapper.fromEntityToResponse(trainer2)).thenReturn(trainerResponse2);
//
//        // Act
//        GetAllTrainersResponse result = userService.getAllTrainers();
//
//        // Assert
//        assertEquals(2, result.getTrainers().size());
//        verify(trainerRepository).findAll();
//        verify(trainerMapper).fromEntityToResponse(trainer1);
//        verify(trainerMapper).fromEntityToResponse(trainer2);
//    }

    @Test
    void deleteUser_DeletesUser() {
        // Arrange
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository).deleteById(userId);
    }

//    @Test
//    void getUserById_Found_ReturnsUser() {
//        // Arrange
//        Integer userId = 1;
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(userId);
//        when(userRepository.findById(userId.longValue())).thenReturn(Optional.of(userEntity));
//
//        // Act
//        Optional<GetUserResponse> result = userService.getUserById(userId);
//
//        // Assert
//        assertTrue(result.isPresent());
//        verify(userRepository).findById(userId.longValue());
//    }

//    @Test
//    void getUserById_NotFound_ReturnsEmpty() {
//        // Arrange
//        Integer userId = 1;
//        when(userRepository.findById(userId.longValue())).thenReturn(Optional.empty());
//
//        // Act
//        Optional<GetUserResponse> result = userService.getUserById(userId);
//
//        // Assert
//        assertFalse(result.isPresent());
//        verify(userRepository).findById(userId.longValue());
//    }
}
