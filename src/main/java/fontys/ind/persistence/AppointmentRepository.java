package fontys.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fontys.ind.persistence.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
}
