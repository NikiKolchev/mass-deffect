package app.repository;

import app.domain.dto.Json.StarImportDto;
import app.domain.entity.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends CrudRepository<Star, Long>{

    //void create(StarImportDto starImportDto);

    Star findOneByName(String name);
}
