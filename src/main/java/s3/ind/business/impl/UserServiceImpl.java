package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s3.ind.business.UserService;
// import s3.ind.business.mapper.UserMapper;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.exception.InvalidUserException;
import s3.ind.configuration.security.token.AccessToken;
import s3.ind.domain.User;
import s3.ind.domain.request.*;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.domain.response.UserResponse;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.converters.UserEntityConverter;
import s3.ind.persistence.entity.UserEntity;

import javax.management.relation.InvalidRoleValueException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;
    private final PasswordEncoder passwordEncoder;
    // private AccessToken accessToken;

    // CREATE
    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserEntity newUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newUser.getUserId()))
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build();

        return userRepository.save(newUser);
    }

    // DELETE
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Integer id) {
        return userEntityConverter.fromEntity(userRepository.getUserEntityByUserId(id));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream()
                .map(userEntityConverter::fromEntity)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    @Transactional
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(request.getUserId());

        if (userEntityOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }

        UserEntity user = userEntityOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);
    }

    // CHECK PERMISSIONS
    @Override
    public void checkUserPermission(int id) {
//        if (!accessToken.getRole().equals("admin")) {
//            if (accessToken.getUserId() != id) {
//                // throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
//            }
//        }

    }
}
