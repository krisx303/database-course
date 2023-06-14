package com.group.travels.api.travel;

import com.group.travels.domain.country.Country;
import com.group.travels.domain.country.CountryStorage;
import com.group.travels.domain.travel.Travel;
import com.group.travels.domain.travel.TravelStorage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/travels")
public class TravelController {

    @Autowired
    private TravelStorage travelStorage;

    @Autowired
    private CountryStorage countryStorage;


    @Operation(description = "Get all travels from database")
    @GetMapping
    ResponseEntity<List<TravelResponse>> getAll() {
        var travels = travelStorage.findAll()
                .stream().map(TravelResponse::new).toList();
        return ResponseEntity.ok(travels);
    }

    @Operation(description = "Get travel details by given ID")
    @GetMapping("/{id}")
    ResponseEntity<TravelResponse> getByID(@PathVariable Long id){
        Travel travel = travelStorage.findByID(id);
        return ResponseEntity.ok(new TravelResponse(travel));
    }

    @Operation(description = "Create new travel with given details")
    @PostMapping
    ResponseEntity<TravelResponse> create(@RequestBody @Valid TravelRequest details) {
        Country country = countryStorage.findByID(details.countryID());
        Travel saved = travelStorage.create(details, country);
        return new ResponseEntity<>(new TravelResponse(saved), HttpStatus.CREATED);
    }

    @Operation(description = "Get all travels which contains query string in name")
    @GetMapping("/search")
    ResponseEntity<List<TravelResponse>> searchByName(@RequestParam("query") String query) {
        var filtered = travelStorage.searchByName(query)
                .stream().map(TravelResponse::new).toList();
        return ResponseEntity.ok(filtered);
    }

    @Operation(description = "Filter travels by provided object TravelSearchRequest")
    @PostMapping("/filter")
    ResponseEntity<List<TravelResponse>> filterTravels(@RequestBody TravelSearchRequest details){
        var filtered = travelStorage.filterTravels(details.countryIDs(), details.travelName(), details.minFreePlaces());
        var formatted = filtered.stream().map(TravelResponse::new).toList();
        return ResponseEntity.ok(formatted);
    }

    @Operation(description = "Update travel details with given id")
    @PutMapping("/{id}")
    ResponseEntity<TravelResponse> update(@PathVariable Long id, @RequestBody @Valid TravelRequest details) {
        Country country = countryStorage.findByID(details.countryID());
        Travel updated = travelStorage.update(id, details, country);
        return ResponseEntity.ok(new TravelResponse(updated));
    }

    @Operation(description = "Delete travel with given id")
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        travelStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
