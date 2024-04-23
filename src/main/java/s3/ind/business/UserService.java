package s3.ind.business;

import s3.ind.domain.User;
import s3.ind.domain.request.users.CreateTrainerRequest;
import s3.ind.domain.request.users.CreateUserRequest;
import s3.ind.domain.request.users.UpdateUserRequest;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.domain.response.GetAllUsersResponse;
import s3.ind.domain.response.user.GetAllTrainersResponse;

import java.util.Optional;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest request);
    CreateUserResponse createTrainer(CreateTrainerRequest request);

    void deleteUser(Long userId);

    Optional<User> getUserById(Integer id);

    GetAllUsersResponse getAllUsers();
    GetAllTrainersResponse getAllTrainers();

    void updateUser(UpdateUserRequest request);

    void checkUserPermission(int id);
}
