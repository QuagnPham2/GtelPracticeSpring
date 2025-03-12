package vn.gtel.gtelpractice.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AirportResponse {
    private String iata;
    private String name;
    private String airportCode;
    private String language;
    private Integer priority;

    public AirportResponse(String iata, String name, String airportCode, String language, Integer priority) {
        this.iata = iata;
        this.name = name;
        this.airportCode = airportCode;
        this.language = language;
        this.priority = priority;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
