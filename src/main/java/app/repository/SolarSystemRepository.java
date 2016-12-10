package app.repository;

import app.domain.dto.Json.SolarSystemImportDto;
import app.domain.entity.SolarSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarSystemRepository extends CrudRepository<SolarSystem, Long>{

    SolarSystem findOneByName(String name);
}
