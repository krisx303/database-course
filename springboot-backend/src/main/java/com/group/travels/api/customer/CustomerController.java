package com.group.travels.api.customer;

import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.customer.CustomerStorage;
import jakarta.validation.Valid;
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
    ResponseEntity<List<CustomerResponse>> getAll() {
        var customers = customerStorage.findAll()
                .stream().map(CustomerResponse::new).toList();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getByID(@PathVariable Long id){
        Customer customer = customerStorage.findByID(id);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

    @PostMapping
    ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequest details){
        Customer saved = customerStorage.create(details);
        return new ResponseEntity<>(new CustomerResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody @Valid CustomerRequest details) {
        Customer updated = customerStorage.update(id, details);
        return ResponseEntity.ok(new CustomerResponse(updated));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
        customerStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/getSavings")
    ResponseEntity<TotalSavings> getSavings(@PathVariable Long id){
        Customer customer = customerStorage.findByID(id);
        var savings= customer.getBookings().stream().mapToDouble(Booking::getSavingsValue).sum();
        return ResponseEntity.ok(new TotalSavings(savings));
    }
}
