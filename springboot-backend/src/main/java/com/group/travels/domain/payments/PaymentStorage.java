package com.group.travels.domain.payments;

import com.group.travels.api.IllegalOperationException;
import com.group.travels.api.travel.TravelRequest;
import com.group.travels.domain.country.Country;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.travel.Travel;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentStorage {

    private final PaymentRepository PaymentRepository;

    public PaymentStorage(PaymentRepository PaymentRepository) {
        this.PaymentRepository = PaymentRepository;
    }

    public List<Payment> findAll() {
        return PaymentRepository.findAll();
    }

    public Payment findByID(Long id) {
        return PaymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    public Payment create(Customer customer, Travel travel){
        Payment toSave = Payment.builder()
                .customer(customer)
                .travel(travel)
                .paymentDate(LocalDateTime.now())
                .price(travel.getPrice())
                .build();

        return PaymentRepository.save(toSave);
    }

    public List<Payment> getPaymentsByCusomerId(Long id){
        return PaymentRepository.findAll().stream().filter(payment -> payment.getCustomer().getCustomer_id() == id).toList();
    }
}
