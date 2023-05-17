package com.group.travels.domain.discount;

import java.util.List;

public class DiscountStorage {

    private final DiscountRepository discountRepository;

    public DiscountStorage(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    public Discount findByID(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
    }

    public Discount create(String discountCode, Integer discountPercentage) {
        Discount toSave = Discount.builder()
                .discountCode(discountCode)
                .discountPercentage(discountPercentage)
                .used(false)
                .build();
        return discountRepository.save(toSave);
    }

    public Discount useDiscount(Long id) {
        Discount discount = findByID(id);
        discount.setUsed(true);
        return discountRepository.save(discount);
    }

    //get discount by code
    public Discount findByCode(String discountCode) {
        return discountRepository.findAll().stream().findFirst().filter(discount -> discount.getDiscountCode().equals(discountCode))
                .orElseThrow(() -> new DiscountNotFoundException(0L));
    }
}
