package com.group.travels.api.travel;

import com.group.travels.domain.travel.Travel;
import com.group.travels.domain.travel.TravelStorage;
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


    @GetMapping
    ResponseEntity<List<Travel>> getAll() {
        var travels = travelStorage.findAll();
        return ResponseEntity.ok(travels);
    }

    @GetMapping("/{id}")
    ResponseEntity<Travel> getByID(@PathVariable Long id){
        Travel travel = travelStorage.findByID(id);
        return ResponseEntity.ok(travel);
    }

    @PostMapping
    ResponseEntity<Travel> create(@RequestBody TravelRequest details){
        Travel saved = travelStorage.create(details);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Travel> update(@PathVariable Long id, @RequestBody TravelRequest details) {
        Travel updated = travelStorage.update(id, details);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        travelStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
