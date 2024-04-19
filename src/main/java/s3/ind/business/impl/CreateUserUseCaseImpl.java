package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s3.ind.business.CreateUserUseCase;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.persistence.UserRepository;
import s3.ind.persistence.entity.UserEntity;


@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserEntity newUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(Long.valueOf(newUser.getUserId()))
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build();

        return userRepository.save(newUser);
    }

}
