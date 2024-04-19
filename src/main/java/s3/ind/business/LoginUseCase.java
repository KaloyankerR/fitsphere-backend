package s3.ind.business;

import s3.ind.domain.request.LoginRequest;
import s3.ind.domain.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
