package fontys.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import fontys.ind.persistence.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
