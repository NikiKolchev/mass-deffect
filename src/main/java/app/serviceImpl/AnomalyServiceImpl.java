package app.serviceImpl;

import app.domain.dto.Json.AnomalyImportJSONDto;
import app.domain.dto.Json.AnomalyVictimsImport;
import app.domain.entity.Anomaly;
import app.domain.entity.Person;
import app.domain.entity.Planet;
import app.parser.interfaces.ModelParser;
import app.repository.AnomalyRepository;
import app.repository.PersonRepository;
import app.repository.PlanetRepository;
import app.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnomalyServiceImpl implements AnomalyService{

    @Autowired
    private AnomalyRepository anomalyRepository;

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void create(AnomalyImportJSONDto anomalyImportJSONDto) {
        Anomaly anomaly = this.modelParser.convert(anomalyImportJSONDto, Anomaly.class);
        Planet teleportPlanet = this.planetRepository.findOneByName(anomalyImportJSONDto.getTeleportPlanet());
        anomaly.setTeleportPlanet(teleportPlanet);
        Planet originPlanet = this.planetRepository.findOneByName(anomalyImportJSONDto.getOriginPlanet());
        anomaly.setOriginPlanet(originPlanet);
        this.anomalyRepository.save(anomaly);
    }

    @Override
    public void create(AnomalyVictimsImport anomayVictimsImport) {
        Anomaly anomaly = this.anomalyRepository.findOne(anomayVictimsImport.getId());
        Person person = this.personRepository.findOneByName(anomayVictimsImport.getPersonName());
        anomaly.addPerson(person);
    }
}
