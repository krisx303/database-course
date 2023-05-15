package com.group.travels.api.country;

import com.group.travels.domain.country.Country;
import com.group.travels.domain.country.CountryStorage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryStorage countryStorage;

    @Operation(description = "Get country details by ID")
    @GetMapping("/{id}")
    ResponseEntity<CountryResponse> getByID(@PathVariable Long id){
        Country country = countryStorage.findByID(id);
        return ResponseEntity.ok(new CountryResponse(country));
    }

    @Operation(description = "Get all countries from database")
    @GetMapping
    ResponseEntity<List<CountryResponse>> getAll(){
        var all = countryStorage.findAll().stream()
                .map(CountryResponse::new).toList();
        return ResponseEntity.ok(all);
    }

    @Operation(description = "Get all countries which contains query string")
    @GetMapping("/search")
    ResponseEntity<List<CountryResponse>> searchByName(@RequestParam("query") String query) {
        var filtered = countryStorage.searchByName(query)
                .stream().map(CountryResponse::new).toList();
        return ResponseEntity.ok(filtered);
    }

    @Operation(description = "Create new country from given details")
    @PostMapping
    ResponseEntity<CountryResponse> create(@RequestBody CountryRequest details){
        Country saved = countryStorage.create(details);
        return new ResponseEntity<>(new CountryResponse(saved), HttpStatus.CREATED);
    }

    @Operation(description = "Update country with given id")
    @PutMapping("/{id}")
    ResponseEntity<CountryResponse> update(@PathVariable Long id, @RequestBody CountryRequest details){
        Country updated = countryStorage.update(id, details);
        return ResponseEntity.ok(new CountryResponse(updated));
    }

    @Operation(description = "Delete country with given id")
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        countryStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
