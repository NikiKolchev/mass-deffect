package app.serviceImpl;

import app.domain.dto.Json.PlanetsImportDto;
import app.domain.entity.Planet;
import app.domain.entity.SolarSystem;
import app.domain.entity.Star;
import app.parser.interfaces.ModelParser;
import app.repository.PlanetRepository;
import app.repository.SolarSystemRepository;
import app.repository.StarRepository;
import app.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService{

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Autowired
    private StarRepository starRepository;

    @Override
    public void create(PlanetsImportDto planetsImportDto) {
        Planet planet = this.modelParser.convert(planetsImportDto, Planet.class);
        Star sun = this.starRepository.findOneByName(planetsImportDto.getSunName());
        planet.setSun(sun);
        SolarSystem solarSystem = this.solarSystemRepository.findOneByName(planetsImportDto.getSolarSystemName());
        planet.setSolarSystem(solarSystem);

        this.planetRepository.save(planet);
    }
}
