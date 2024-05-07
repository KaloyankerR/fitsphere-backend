package fontys.ind.business.impl;

import fontys.ind.domain.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fontys.ind.business.LoginUseCase;
import fontys.ind.business.exception.InvalidCredentialsException;
import fontys.ind.configuration.security.token.AccessTokenEncoder;
import fontys.ind.configuration.security.token.impl.AccessTokenImpl;
import fontys.ind.domain.request.LoginRequest;
import fontys.ind.domain.response.LoginResponse;
import fontys.ind.persistence.UserRepository;
import fontys.ind.persistence.entity.UserEntity;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = Optional.ofNullable(userRepository.findByEmail(loginRequest.getEmail()))
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private String generateAccessToken(UserEntity user) {
        Integer userId = user.getUserId();
        RoleEnum role = user.getRole();

        if (role == null) {
            throw new InvalidCredentialsException();
        }

        return accessTokenEncoder.encode(new AccessTokenImpl(user.getEmail(), Long.valueOf(userId), String.valueOf(role)));
    }
}
