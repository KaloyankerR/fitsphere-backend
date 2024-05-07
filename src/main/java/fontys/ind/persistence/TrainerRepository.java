package fontys.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fontys.ind.persistence.entity.TrainerEntity;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}
