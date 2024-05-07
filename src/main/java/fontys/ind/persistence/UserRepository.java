package fontys.ind.persistence;

import fontys.ind.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByUserId(Integer userId);
    UserEntity findByEmail(String email);
    boolean existsByUserId(Integer userId);
    boolean existsByEmail(String email);
}
