package app.repository;

import app.domain.dto.Json.StarImportDto;
import app.domain.entity.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends CrudRepository<Star, Long>{

    Star findOneByName(String name);
}
