package app.serviceImpl;

import app.domain.dto.Json.StarImportDto;
import app.domain.entity.SolarSystem;
import app.domain.entity.Star;
import app.parser.interfaces.ModelParser;
import app.repository.SolarSystemRepository;
import app.repository.StarRepository;
import app.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService{

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Override
    public void create(StarImportDto starImportDto) {
        Star star = this.modelParser.convert(starImportDto, Star.class);
        SolarSystem solarSystem = this.solarSystemRepository.findOneByName(starImportDto.getSolarSystemName());
        star.setSolarSystem(solarSystem);
        this.starRepository.save(star);
    }

}
