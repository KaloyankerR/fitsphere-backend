package fontys.ind.business.impl;

import fontys.ind.business.exception.InvalidUserException;
import fontys.ind.business.mappers.ClientMapper;
import fontys.ind.business.mappers.TrainerMapper;
import fontys.ind.domain.RoleEnum;
import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.domain.response.ApiWrapperResponse;
import fontys.ind.persistence.ClientRepository;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import fontys.ind.business.exception.EmailAlreadyExistsException;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.request.user.UpdateUserRequest;
import fontys.ind.domain.response.user.*;
import fontys.ind.persistence.UserRepository;
import fontys.ind.persistence.entity.UserEntity;
import fontys.ind.business.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private TrainerMapper trainerMapper;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TrainerRepository trainerRepository;
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

    @Test
    void createUser_ShouldReturnCreateUserResponse() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setRole("CLIENT");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUserId(1);

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(clientMapper.fromRequestToEntity(any(CreateUserRequest.class))).thenReturn(clientEntity);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        CreateUserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals(1L, response.getUserId());
    }

    @Test
    void createUser_ShouldThrowEmailAlreadyExistsException() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(request));
    }

    @Test
    void deleteUser_ShouldDeleteUser() {
        doNothing().when(userRepository).deleteById(any(Long.class));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void getUserById_ShouldReturnGetUserResponse() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);
        userEntity.setRole(RoleEnum.CLIENT);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUserId(1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        when(clientMapper.fromEntityToResponse(any(ClientEntity.class))).thenReturn(new GetClientResponse());

        Optional<? extends GetUserResponse> response = userService.getUserById(1);

        assertTrue(response.isPresent());
    }

    @Test
    void getUserById_ShouldThrowEntityNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    void getUsersByRole_ShouldReturnClients() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUserId(1);

        when(clientRepository.findAll()).thenReturn(Collections.singletonList(clientEntity));
        when(clientMapper.fromEntityToResponse(any(ClientEntity.class))).thenReturn(new GetClientResponse());

        ApiWrapperResponse response = userService.getUsersByRole("CLIENT");

        assertNotNull(response);
        assertInstanceOf(GetAllClientsResponse.class, response);
    }

    @Test
    void getUsersByRole_ShouldThrowInvalidUserException() {
        assertThrows(InvalidUserException.class, () -> userService.getUsersByRole("INVALID_ROLE"));
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));
        when(userMapper.fromEntityToResponse(any(UserEntity.class))).thenReturn(new GetUserResponse());

        GetAllUsersResponse response = userService.getAllUsers();

        assertNotNull(response);
        assertEquals(1, response.getUsers().size());
    }

    @Test
    void updateUser_ShouldUpdateUser() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(1L);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        userService.updateUser(request);

        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void updateUser_ShouldThrowInvalidUserException() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InvalidUserException.class, () -> userService.updateUser(request));
    }

//    @Test
//    void createTrainer_ShouldReturnCreateUserResponse() {
//        CreateTrainerRequest request = new CreateTrainerRequest();
//        request.setEmail("trainer@example.com");
//        request.setPassword("password");
//
//        TrainerEntity trainerEntity = new TrainerEntity();
//        trainerEntity.setUserId(1);
//
//        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
//        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
//        when(trainerMapper.fromRequestToEntity(any(CreateTrainerRequest.class))).thenReturn(trainerEntity);
//        when(trainerRepository.save(any(TrainerEntity.class))).thenReturn(trainerEntity);
//
//        CreateUserResponse response = userService.createTrainer(request);
//
//        // assertNotNull(response);
//        assertEquals(1L, response.getUserId());
//    }

    @Test
    void createTrainer_ShouldThrowEmailAlreadyExistsException() {
        CreateTrainerRequest request = new CreateTrainerRequest();
        request.setEmail("trainer@example.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.createTrainer(request));
    }

    @Test
    void getAllTrainers_ShouldReturnGetAllTrainersResponse() {
        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setUserId(1);
        GetTrainerResponse getTrainerResponse = new GetTrainerResponse();
        getTrainerResponse.setId(1);

        when(trainerRepository.findAll()).thenReturn(Collections.singletonList(trainerEntity));
        when(trainerMapper.fromEntityToResponse(any(TrainerEntity.class))).thenReturn(getTrainerResponse);

        GetAllTrainersResponse response = userService.getAllTrainers();

        assertNotNull(response);
        assertEquals(1, response.getTrainers().size());
        assertEquals(1, response.getTrainers().get(0).getId());
    }
}
