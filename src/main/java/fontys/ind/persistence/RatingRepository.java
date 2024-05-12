package fontys.ind.persistence;

import fontys.ind.persistence.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    RatingEntity findByTrainerUserIdAndClientUserId(Integer trainerId, Integer clientId);
    List<RatingEntity> findAllByTrainerUserId(Integer trainerId);
}
