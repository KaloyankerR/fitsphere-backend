package s3.ind.business;

import s3.ind.domain.request.user.CreateTrainerRequest;
import s3.ind.domain.request.user.CreateUserRequest;
import s3.ind.domain.request.user.UpdateUserRequest;
import s3.ind.domain.response.user.CreateUserResponse;
import s3.ind.domain.response.user.GetAllUsersResponse;
import s3.ind.domain.response.user.GetAllTrainersResponse;
import s3.ind.domain.response.user.GetUserResponse;

import java.util.Optional;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest request);
    CreateUserResponse createTrainer(CreateTrainerRequest request);

    void deleteUser(Long userId);

    Optional<GetUserResponse> getUserById(Integer id);

    GetAllUsersResponse getAllUsers();
    GetAllTrainersResponse getAllTrainers();

    void updateUser(UpdateUserRequest request);

    void checkUserPermission(int id);
}
