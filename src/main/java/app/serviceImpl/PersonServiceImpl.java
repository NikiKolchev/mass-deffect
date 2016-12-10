package app.serviceImpl;

import app.domain.dto.Json.PersonImportDto;
import app.domain.entity.Person;
import app.domain.entity.Planet;
import app.parser.interfaces.ModelParser;
import app.repository.PersonRepository;
import app.repository.PlanetRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public void create(PersonImportDto personImportDto) {
        Person person = this.modelParser.convert(personImportDto, Person.class);
        Planet planet = this.planetRepository.findOneByName(personImportDto.getHomePlanet());
        person.setPlanet(planet);
        this.personRepository.save(person);
    }
}
