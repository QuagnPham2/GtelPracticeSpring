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

    @GetMapping("/pageable")
    public ResponseEntity<List<AirportResponse>> getAirports(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<AirportResponse> airports = airportService.getAirports(page, size);
        return ResponseEntity.ok(airports);
    }

    @GetMapping
    public ApiResponse<List<Airport>> getAllAirports() {
        ApiResponse<List<Airport>> apiResponse = new ApiResponse();
        apiResponse.setResult(airportService.getAllAirports());
        return apiResponse;
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countAirports() {
        long count =  airportService.countAirports();
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).build();
    }


    @GetMapping("/{iata}")
    public ApiResponse<AirportResponse> getAirport(@PathVariable String iata) {
        ApiResponse<AirportResponse> apiResponse = new ApiResponse();
        apiResponse.setResult(airportService.getAirport(iata));
        return apiResponse;
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

    @DeleteMapping("/{iata}")
    public ResponseEntity<?> deleteAirport(@PathVariable String iata) {
        if (iata == null){
            return ResponseEntity.badRequest().body("AirportId is null");
        }
        airportService.deleteAirport(iata);
        return ResponseEntity.ok("Airport deleted successfully");
    }


    //Native Query
    @GetMapping("/searchNative")
    public ApiResponse<List<Airport>> searchAirports(@RequestParam String keyword){
        ApiResponse<List<Airport>> apiResponse = new ApiResponse();
        apiResponse.setResult(airportService.searchAirportN(keyword));
        return apiResponse;
    }


    //Custom Query
    @GetMapping("/searchCustom")
    public ApiResponse<List<Airport>> searchAirportCustom(@RequestParam String keyword){
        ApiResponse<List<Airport>> apiResponse = new ApiResponse();
        apiResponse.setResult(airportService.searchAirportC(keyword));
        return apiResponse;
    }
}
