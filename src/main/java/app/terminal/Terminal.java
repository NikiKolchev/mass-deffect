package app.terminal;

import app.config.Confing;
import app.domain.dto.Json.*;
import app.io.interfaces.ConsoleIO;
import app.parser.interfaces.FileParser;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    @Autowired
    private AnomalyService anomalyService;

    @Autowired
    private ConsoleIO consoleIO;

    @Autowired
    private SolarSystemService solarSystemService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PlanetService planetService;

    @Autowired
    private StarService starService;

    @Override
    public void run(String... strings) throws Exception {
        this.importSolarSystemFromJson();
        this.importStarFromJson();
        this.importPlanetFromJson();
        this.importPersonFromJson();
        this.importAnomaliesFromJson();
        this.importAnomalyVictimsFromJSON();
    }


    private void importSolarSystemFromJson() {
        SolarSystemImportDto[] solarSystemImportDtos = null;

        try {
            solarSystemImportDtos = this.jsonParser.read(SolarSystemImportDto[].class, Confing.SOLAR_SYSTEM_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (SolarSystemImportDto dto : solarSystemImportDtos) {
            this.solarSystemService.create(dto);
            this.consoleIO.write(String.format("Successfully imported Solar System %s", dto.getName()));
        }
    }

    private void importStarFromJson() {
        StarImportDto[] starImportDtos = null;
        try{
            starImportDtos = this.jsonParser.read(StarImportDto[].class, Confing.STAR_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (StarImportDto starImportDto : starImportDtos) {
            try {
                this.starService.create(starImportDto);
                this.consoleIO.write(String.format("Successfully imported %s", starImportDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write("Error: invalid data.");
            }
        }
    }

    private void importPlanetFromJson() {
        PlanetsImportDto[] planetsImportDtos = null;

        try {
            planetsImportDtos = this.jsonParser.read(PlanetsImportDto[].class, Confing.PLANET_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PlanetsImportDto planetsImportDto : planetsImportDtos) {
            try{
                this.planetService.create(planetsImportDto);
                this.consoleIO.write("Successfully imported data.");
            } catch (Exception e){
                this.consoleIO.write("Error: invalid data.");
            }
        }
    }

    private void importPersonFromJson() {
        PersonImportDto[] personImportDtos = null;

        try{
            personImportDtos = this.jsonParser.read(PersonImportDto[].class, Confing.PERSON_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PersonImportDto personImportDto : personImportDtos) {
            try{
                this.personService.create(personImportDto);
                this.consoleIO.write("Successfully imported data.");
            } catch (Exception e) {
                this.consoleIO.write("Error: Invalid data.");
            }
        }
    }

    private void importAnomalyVictimsFromJSON(){
        AnomalyVictimsImport[] anomalyVictimsDtos = null;
        try {
            anomalyVictimsDtos = this.jsonParser.read(AnomalyVictimsImport[].class, Confing.ANOMALY_VICTIMS_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AnomalyVictimsImport anomalyVictimsDto : anomalyVictimsDtos) {
            try{
                this.anomalyService.create(anomalyVictimsDto);
                this.consoleIO.write(String.format("Successfully imported Anomaly."));
            } catch (Exception e){
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }


    private void importAnomaliesFromJson(){
        AnomalyImportJSONDto[] anomalyImportJSONDtos = null;

        try{
            anomalyImportJSONDtos = this.jsonParser.read(AnomalyImportJSONDto[].class, Confing.ANOMALY_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AnomalyImportJSONDto anomalyImportJSONDto : anomalyImportJSONDtos) {
            try {
                this.anomalyService.create(anomalyImportJSONDto);
                this.consoleIO.write("Successfully imported Anomaly");
            } catch (Exception e) {
                this.consoleIO.write("Error: Invalid data.");
            }
        }
    }
}
