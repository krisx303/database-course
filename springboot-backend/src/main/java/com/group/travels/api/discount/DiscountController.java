package com.group.travels.api.discount;

import com.group.travels.domain.booking.BookingStorage;
import com.group.travels.domain.customer.CustomerStorage;
import com.group.travels.domain.discount.Discount;
import com.group.travels.domain.discount.DiscountStorage;
import com.group.travels.domain.log.LogStorage;
import com.group.travels.domain.payments.PaymentStorage;
import com.group.travels.domain.travel.TravelStorage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    @Autowired
    private BookingStorage bookingStorage;

    @Autowired
    private TravelStorage travelStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private LogStorage logStorage;

    @Autowired
    private PaymentStorage paymentStorage;

    @Autowired
    private DiscountStorage discountStorage;

    @Operation(description = "Creates new Discount")
    @PostMapping
    ResponseEntity<Discount> create(@RequestBody DiscountRequest details) {
        Discount discount = discountStorage.create(details.discountCode(), details.discountPercentage());
        return ResponseEntity.ok(discount);
    }

}
