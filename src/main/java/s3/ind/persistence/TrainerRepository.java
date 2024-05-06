package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.TrainerEntity;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}
