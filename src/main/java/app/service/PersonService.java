package app.service;


import app.domain.dto.Json.PersonImportDto;

public interface PersonService {

    void create(PersonImportDto personImportDto);

}
