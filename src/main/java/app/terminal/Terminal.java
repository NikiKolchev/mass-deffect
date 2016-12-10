package app.terminal;

import app.config.Confing;
import app.domain.dto.Json.AnomalyImportJSONDto;
import app.io.interfaces.ConsoleIO;
import app.parser.interfaces.FileParser;
import app.service.AnomalyService;
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

    @Override
    public void run(String... strings) throws Exception {
        this.importAnomaliesFromJson();
    }

    private void importAnomaliesFromJson(){
        AnomalyImportJSONDto[] anomalyImportJSONDtos = null;

        try{
            anomalyImportJSONDtos = this.jsonParser.read(AnomalyImportJSONDto[].class, Confing.ANOMALY_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
