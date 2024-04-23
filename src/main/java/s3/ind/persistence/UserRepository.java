package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByUserId(Integer userId);
    UserEntity findByEmail(String email);
    boolean existsByUserId(Integer userId);
    boolean existsByEmail(String email);
}
