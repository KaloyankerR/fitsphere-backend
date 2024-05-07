package fontys.ind.business;

import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.request.user.UpdateUserRequest;
import fontys.ind.domain.response.user.CreateUserResponse;
import fontys.ind.domain.response.user.GetAllUsersResponse;
import fontys.ind.domain.response.user.GetAllTrainersResponse;
import fontys.ind.domain.response.user.GetUserResponse;

import java.util.Optional;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest request);
    CreateUserResponse createTrainer(CreateTrainerRequest request);

    void deleteUser(Long userId);

    Optional<GetUserResponse> getUserById(Integer id);

    GetAllUsersResponse getAllUsers();
    GetAllTrainersResponse getAllTrainers();

    void updateUser(UpdateUserRequest request);
}
