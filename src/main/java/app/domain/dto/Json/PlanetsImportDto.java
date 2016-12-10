package app.domain.dto.Json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlanetsImportDto implements Serializable{

    @Expose
    private String name;

    @Expose
    @SerializedName(value = "sun")
    private String sunName;

    @Expose
    @SerializedName(value = "solarSystem")
    private String solarSystemName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSunName() {
        return sunName;
    }

    public void setSunName(String sunName) {
        this.sunName = sunName;
    }

    public String getSolarSystemName() {
        return solarSystemName;
    }

    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }
}
