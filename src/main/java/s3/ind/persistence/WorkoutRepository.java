package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.WorkoutEntity;

public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Integer> {
    boolean existsByTitle(String title);

}
