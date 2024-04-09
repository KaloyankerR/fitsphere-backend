package s3.ind.business;

import s3.ind.domain.request.UserRequest;
import s3.ind.domain.response.UserResponse;

// import java.util.List;
import java.util.List;
import java.util.Map;

public interface UserService {
    boolean createUser(UserRequest userRequest, String password);
    boolean deleteUser(int id);
    UserResponse getUser(int id);
    List<UserResponse> getAllUsers();
    boolean updateUser(int id, UserRequest userUpdates);
    void checkUserPermission(int id);
}
