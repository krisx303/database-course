package com.group.travels.domain.discount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Discount findByDiscountCode(String discountCode);
}
