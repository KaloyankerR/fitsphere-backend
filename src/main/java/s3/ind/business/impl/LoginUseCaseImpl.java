package s3.ind.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s3.ind.business.LoginUseCase;
import s3.ind.business.exception.InvalidCredentialsException;
import s3.ind.configuration.security.token.AccessTokenEncoder;
import s3.ind.configuration.security.token.impl.AccessTokenImpl;
import s3.ind.domain.request.LoginRequest;
import s3.ind.domain.response.LoginResponse;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.entity.UserEntity;


@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = Long.valueOf(user != null ? user.getUserId() : null);
        String role = user.getRole();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), userId, role));
    }

}
