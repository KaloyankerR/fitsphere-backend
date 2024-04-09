package s3.ind.business;

import s3.ind.domain.request.UserLoginRequest;
import s3.ind.domain.response.UserLoginResponse;

public interface AuthenticationService {
    UserLoginResponse login(UserLoginRequest request);
}
