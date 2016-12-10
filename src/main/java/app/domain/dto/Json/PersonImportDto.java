package app.domain.dto.Json;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PersonImportDto implements Serializable{

    @Expose
    private String name;

    @Expose
    private String homePlanet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }
}
