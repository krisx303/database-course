package com.group.travels.domain.payments;

import com.group.travels.api.IllegalOperationException;
import com.group.travels.api.travel.TravelRequest;
import com.group.travels.domain.booking.Booking;
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

    public Payment create(Booking booking, double price_discounted) {
        Payment toSave;
        if(price_discounted==-1){
             toSave = Payment.builder()
                    .booking(booking)
                    .paymentDate(LocalDateTime.now())
                    .price(booking.getTravel().getPrice())
                    .build();
        }else{
             toSave = Payment.builder()
                    .booking(booking)
                    .paymentDate(LocalDateTime.now())
                    .price((int) price_discounted)
                    .build();
        }


        return PaymentRepository.save(toSave);
    }

    public List<Payment> getPaymentsByCusomerId(Long id){
        return PaymentRepository.findAll().stream().filter(payment -> payment.getBooking().getCustomer().getCustomer_id() == id).toList();
    }
}
