package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.DeleteUserUseCase;
import s3.ind.persistence.UserRepository;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
