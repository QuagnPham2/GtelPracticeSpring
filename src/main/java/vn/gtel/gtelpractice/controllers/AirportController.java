package vn.gtel.gtelpractice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gtel.gtelpractice.dto.request.AirportRequest;
import vn.gtel.gtelpractice.dto.response.AirportResponse;
import vn.gtel.gtelpractice.dto.response.ApiResponse;
import vn.gtel.gtelpractice.models.Airport;
import vn.gtel.gtelpractice.services.AirportService;

import java.util.List;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAirports(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<AirportResponse> airports = airportService.getAirports(page, size);
        return ResponseEntity.ok(airports);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countAirports() {
        long count =  airportService.countAirports();

        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).build();
    }


    @GetMapping("/{iata}")
    public AirportResponse getAirport(@PathVariable String iata) {
        return airportService.getAirport(iata);
    }

    @PostMapping
    public ApiResponse<Airport> createAirport(@RequestBody AirportRequest airportRequest) {
        ApiResponse<Airport> apiResponse =new ApiResponse();
        apiResponse.setResult(airportService.createAirport(airportRequest));
        return apiResponse;
    }

    @PutMapping("/{iata}")
    public ApiResponse<Airport> updateAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        ApiResponse<Airport> apiResponse =new ApiResponse();
        apiResponse.setResult(airportService.updateAirports(iata, airportRequest));
        return apiResponse;
    }

    @PatchMapping("/{iata}")
    public ApiResponse<Airport> updatePatchAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        ApiResponse<Airport> apiResponse =new ApiResponse();
        apiResponse.setResult(airportService.updatePatchAirports(iata, airportRequest));
        return apiResponse;
    }
//    public ResponseEntity<String> updatePatchAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
//        airportService.updatePatchAirports(iata, airportRequest);
//        return ResponseEntity.ok("Updated Airport successfully");
//    }


    @DeleteMapping("/{iata}")
    public ResponseEntity<?> deleteAirport(@PathVariable String iata) {
        if (iata == null){
            return ResponseEntity.badRequest().body("AirportId is null");
        }
        airportService.deleteAirport(iata);
        return ResponseEntity.ok("Airport deleted successfully");
    }
}
