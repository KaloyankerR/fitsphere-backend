package s3.ind.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import s3.ind.persistence.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
