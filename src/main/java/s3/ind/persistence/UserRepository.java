package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // UserEntity findByUserId(Integer userId);
    boolean existsByUserId(Integer userId);
}
