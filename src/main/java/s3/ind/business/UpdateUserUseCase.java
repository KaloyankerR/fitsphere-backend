package s3.ind.business;

import s3.ind.domain.request.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
