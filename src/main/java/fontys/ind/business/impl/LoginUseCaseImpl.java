package fontys.ind.business.impl;

import fontys.ind.business.LoginUseCase;
import fontys.ind.business.exception.InvalidCredentialsException;
import fontys.ind.configuration.security.token.AccessTokenEncoder;
import fontys.ind.configuration.security.token.impl.AccessTokenImpl;
import fontys.ind.domain.request.LoginRequest;
import fontys.ind.domain.response.LoginResponse;
import fontys.ind.persistence.UserRepository;
import fontys.ind.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
        String role = String.valueOf(user.getRole());

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), userId, role));
    }

}
