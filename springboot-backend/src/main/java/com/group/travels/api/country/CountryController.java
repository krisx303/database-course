package com.group.travels.api.country;

import com.group.travels.domain.country.Country;
import com.group.travels.domain.country.CountryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    @Autowired
    private CountryStorage countryStorage;

    @GetMapping("/{id}")
    ResponseEntity<Country> getByID(@PathVariable Long id){
        Country country = countryStorage.findByID(id);
        return ResponseEntity.ok(country);
    }

    @GetMapping
    ResponseEntity<List<Country>> getAll(){
        var all = countryStorage.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    ResponseEntity<Country> create(@RequestBody CountryRequest details){
        Country saved = countryStorage.create(details);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryRequest details){
        Country updated = countryStorage.update(id, details);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        countryStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
