package s3.ind.business;

import s3.ind.domain.User;
import s3.ind.domain.request.UserRequest;

import java.util.List;

public interface UserService {
    boolean createUser(UserRequest userRequest, String password);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    List<User> getAllUsers();

    void updateUser(int id, User userUpdates) throws Exception;

    void checkUserPermission(int id);
}
