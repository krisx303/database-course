package com.group.travels.api.customer;

import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.customer.CustomerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerStorage customerStorage;

    @GetMapping
    ResponseEntity<List<Customer>> getAll() {
        var customers = customerStorage.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getByID(@PathVariable Long id){
        Customer customer = customerStorage.findByID(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    ResponseEntity<Customer> create(@RequestBody CustomerRequest details){
        Customer saved = customerStorage.create(details);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerRequest details) {
        Customer updated = customerStorage.update(id, details);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        customerStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
