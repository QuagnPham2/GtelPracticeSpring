package vn.gtel.gtelpractice.dto.request;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportRequest {
    private String iata;
    private String name;
    private String airportCode;
    private String language;
    private Integer priority;

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
