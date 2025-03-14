package com.gtel.srpingtutorial.controller;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.model.Airport;
import com.gtel.srpingtutorial.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportResponse> getAirports(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        return airportService.getAirports(page, size);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countAirports(@RequestParam(defaultValue = "5") int size) {
        int totalAirport =  airportService.countAirports();
        int totalPages = (int) Math.ceil((double) totalAirport / size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalAirport));
        headers.add("X-Total-Pages", String.valueOf(totalPages));
        headers.add("X-Page-Size", String.valueOf(size));

        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("/{iata}")
    public AirportResponse getAirport(@PathVariable String iata) {
        return airportService.getAirport(iata);

    }

    @PostMapping
    public AirportResponse createAirport(@RequestBody AirportRequest airportRequest) {
        return airportService.createAirport(airportRequest);
    }

    @PutMapping("/{iata}")
    public AirportResponse updateAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        return airportService.updateAirports(iata, airportRequest);
    }

    @PatchMapping("/{iata}")
    public AirportResponse updatePatchAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        return airportService.updatePatchAirports(iata, airportRequest);
    }

    @DeleteMapping("/{iata}")
    public void deleteAirport(@PathVariable String iata) {
        airportService.deleteAirport(iata);
    }
}
