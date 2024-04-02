package s3.ind.bussiness.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.bussiness.AuthenticationService;
import s3.ind.domain.request.UserLoginRequest;
import s3.ind.domain.response.UserLoginResponse;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        return UserLoginResponse.builder().accessToken("asd123").build();
    }
}
