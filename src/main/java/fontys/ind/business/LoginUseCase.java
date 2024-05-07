package fontys.ind.business;

import fontys.ind.domain.request.LoginRequest;
import fontys.ind.domain.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
