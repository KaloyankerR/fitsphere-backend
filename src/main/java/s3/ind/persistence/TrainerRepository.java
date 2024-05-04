package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.UserEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}
