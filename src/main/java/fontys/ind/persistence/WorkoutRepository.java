package fontys.ind.persistence;

import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Integer> {
    boolean existsByTitle(String title);
    List<WorkoutEntity> findAllByTrainer(TrainerEntity trainer);
}
