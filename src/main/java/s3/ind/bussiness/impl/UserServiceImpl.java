package s3.ind.bussiness.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s3.ind.bussiness.UserService;
import s3.ind.bussiness.mapper.UserMapper;
// import s3.ind.configuration.security.PasswordEncoderConfig;
import s3.ind.domain.request.UserRequest;
import s3.ind.domain.response.UserResponse;
import s3.ind.persistence.UserRepository;

import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRequest userRequest, String password) {
        if(!userRepository.existsById(userRequest.getId())){
            String encodedPassword = passwordEncoder.encode(password);
            userRequest.setPassword(encodedPassword);
        }
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public UserResponse getUser(int id) {
        return null;
    }

    @Override
    public Map<Integer, UserResponse> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(int id, UserRequest userUpdates) {

    }

    @Override
    public void checkUserPermission(int id) {

    }
}
