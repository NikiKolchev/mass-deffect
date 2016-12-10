package app.repository;

import app.domain.entity.Anomaly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnomalyRepository extends CrudRepository<Anomaly, Long>{


}
