package s3.ind.bussiness;

import s3.ind.domain.request.UserRequest;
import s3.ind.domain.response.UserResponse;

// import java.util.List;
import java.util.Map;

public interface UserService {
    void createUser(UserRequest userRequest, String password);
    void deleteUser(int id);
    UserResponse getUser(int id);
    Map<Integer, UserResponse> getAllUsers();
    void updateUser(int id, UserRequest userUpdates);
    void checkUserPermission(int id);
}
