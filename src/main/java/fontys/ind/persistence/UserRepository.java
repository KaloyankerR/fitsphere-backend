package fontys.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fontys.ind.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByUserId(Integer userId);
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
