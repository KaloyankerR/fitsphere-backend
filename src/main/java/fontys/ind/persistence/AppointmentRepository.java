package fontys.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fontys.ind.persistence.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> findAllByTrainerUserId(Integer id);
    List<AppointmentEntity> findAllByClientUserId(Integer id);
}
