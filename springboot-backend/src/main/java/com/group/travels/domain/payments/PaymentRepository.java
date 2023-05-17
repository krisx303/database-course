package com.group.travels.domain.payments;

import com.group.travels.domain.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment ,Long> {

}
