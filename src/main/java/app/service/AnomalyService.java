package app.service;


import app.domain.dto.Json.AnomalyImportJSONDto;
import app.domain.dto.Json.AnomalyVictimsImport;

public interface AnomalyService {

    void create(AnomalyImportJSONDto anomalyImportJSONDto);

    void create(AnomalyVictimsImport anomayVictimsImport);

}
