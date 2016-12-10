package app.serviceImpl;

import app.domain.dto.Json.SolarSystemImportDto;
import app.domain.entity.SolarSystem;
import app.parser.interfaces.ModelParser;
import app.repository.SolarSystemRepository;
import app.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolarSystemServiceImpl implements SolarSystemService{

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Override
    public void create(SolarSystemImportDto solarSystemImportDto) {
        SolarSystem solarSystem = this.modelParser.convert(solarSystemImportDto, SolarSystem.class);
        this.solarSystemRepository.save(solarSystem);
    }
}
