package s3.ind.business;

import s3.ind.domain.User;
import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.request.UpdateUserRequest;
import s3.ind.domain.request.UserRequest;
import s3.ind.domain.response.CreateUserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest request);

    void deleteUser(Long userId);

    User getUserById(Integer id);

    List<User> getAllUsers();

    void updateUser(UpdateUserRequest request);

    void checkUserPermission(int id);
}
