package com.group.travels.domain.discount;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "DISCOUNTS")
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DISCOUNT_CODE")
    private String discountCode;

    @Column(name = "DISCOUNT_PERCENTAGE")
    private Integer discountPercentage;

    @Column(name = "USED")
    private Boolean used;

    public double calculateDiscount(double price) {
        return price * (1 - discountPercentage / 100.0);
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public double calculateSavings(Double price) {
        return price * (discountPercentage / 100.0);
    }
}
