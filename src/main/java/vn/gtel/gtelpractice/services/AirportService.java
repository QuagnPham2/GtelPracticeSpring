package vn.gtel.gtelpractice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import vn.gtel.gtelpractice.dto.request.AirportRequest;
import vn.gtel.gtelpractice.dto.response.AirportResponse;
import vn.gtel.gtelpractice.models.Airport;
import vn.gtel.gtelpractice.repositories.AirportRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;


    public List<AirportResponse> getAirports(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Airport> airportPage = airportRepository.findAll(pageable);

        return airportPage.getContent().stream().map(airport -> new AirportResponse(
                airport.getIata(),
                airport.getName(),
                airport.getAirportCode(),
                airport.getLanguage(),
                airport.getPriority()
        )).toList();
    }

    public long countAirports() {
        return airportRepository.count();
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public AirportResponse getAirport(String iata) {
        Airport airport = airportRepository.findById(iata).orElseThrow(()-> new RuntimeException("Airport nots found"));
        return new AirportResponse(airport.getIata(), airport.getName(), airport.getAirportCode(), airport.getLanguage(), airport.getPriority());
    }

    public Airport createAirport(AirportRequest request) {
        if(request == null){
            throw new IllegalArgumentException("AirportRequest cannot be null");
        }
        if(airportRepository.existsByAirportCode(request.getAirportCode())){
            throw new IllegalArgumentException("Airport code already exists");
        }

        Airport airport = new Airport();

        airport.setIata(request.getIata());
        airport.setName(request.getName());
        airport.setAirportCode(request.getAirportCode());
        airport.setLanguage(request.getLanguage());
        airport.setPriority(request.getPriority());

        return airportRepository.save(airport);
    }

    public void deleteAirport(String iata) {
        airportRepository.deleteById(iata);
    }

    public Airport updateAirports(@PathVariable String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("Airport not found"));
        airport.setName(airportRequest.getName());
        airport.setAirportCode(airportRequest.getAirportCode());
        airport.setLanguage(airportRequest.getLanguage());
        airport.setPriority(airportRequest.getPriority());

        return airportRepository.save(airport);

    }

    public Airport updatePatchAirports(String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("Airport not found"));
        if(airportRequest.getName() != null){
            airport.setName(airportRequest.getName());
        }
        if(airportRequest.getAirportCode() != null){
            airport.setAirportCode(airportRequest.getAirportCode());
        }
        if(airportRequest.getLanguage() != null){
            airport.setLanguage(airportRequest.getLanguage());
        }
        if(airportRequest.getPriority() != null) {
            airport.setPriority(airportRequest.getPriority());
        }
        return airportRepository.save(airport);
    }
}
