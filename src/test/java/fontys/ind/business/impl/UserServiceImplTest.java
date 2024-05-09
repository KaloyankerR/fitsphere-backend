package fontys.ind.business.impl;

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

}
