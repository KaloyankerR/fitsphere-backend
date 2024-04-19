package s3.ind.business;

import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.response.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
