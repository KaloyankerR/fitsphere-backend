//package fontys.ind.persistence;
//
//import fontys.ind.persistence.entity.RefreshTokenEntity;
//import fontys.ind.persistence.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
//    Optional<RefreshTokenEntity> findByToken(String token);
//    void deleteByUser(UserEntity user);
//}
