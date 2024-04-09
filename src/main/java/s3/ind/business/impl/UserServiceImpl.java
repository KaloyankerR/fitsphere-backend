package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s3.ind.business.UserService;
// import s3.ind.business.mapper.UserMapper;
import s3.ind.configuration.security.token.AccessToken;
import s3.ind.domain.request.AdminRequest;
import s3.ind.domain.request.ClientRequest;
import s3.ind.domain.request.TrainerRequest;
import s3.ind.domain.request.UserRequest;
import s3.ind.domain.response.UserResponse;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.entity.UserEntity;

import javax.management.relation.InvalidRoleValueException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    // private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    // private AccessToken accessToken;

    @Override
    public boolean createUser(UserRequest userRequest, String password) {
//        if (!userRepository.existsByUserId(userRequest.getId())) {
//            String encodedPassword = passwordEncoder.encode(password);
//            userRequest.setPassword(encodedPassword);
//
//            switch (userRequest.getRole()) {
//                case "admin" -> {
//                    AdminRequest adminRequest = userMapper.toAdminRequest(userRequest);
//                    UserEntity userEntity = userMapper.toEntity(adminRequest);
//                    userRepository.save(userEntity);
//                }
//                case "trainer" -> {
//                    TrainerRequest trainerRequest = userMapper.toTrainerRequest(userRequest);
//                    UserEntity userEntity = userMapper.toEntity(trainerRequest);
//                    userRepository.save(userEntity);
//                }
//                case "client" -> {
//                    ClientRequest clientRequest = userMapper.toClientRequest(userRequest);
//                    UserEntity userEntity = userMapper.toEntity(clientRequest);
//                    userRepository.save(userEntity);
//                }
//                default -> {
//                    // throw new InvalidRoleValueException("Invalid Role");
//                }
//            }
//            return true;
//        } else {
//            // throw new InvalidRoleValueException("Invalid Role"); change exception
//            return false;
//        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public UserResponse getUser(int id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

//        return users.stream()
//                .map(userMapper::toUserResponse)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public boolean updateUser(int id, UserRequest userUpdates) {
        return false;
    }

    @Override
    public void checkUserPermission(int id) {
//        if (!accessToken.getRole().equals("admin")) {
//            if (accessToken.getUserId() != id) {
//                // throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
//            }
//        }

    }
}
