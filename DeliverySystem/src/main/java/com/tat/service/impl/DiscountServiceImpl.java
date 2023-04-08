/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.tat.pojos.Discount;
import com.tat.repository.DiscountRepository;
import com.tat.service.DiscountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class DiscountServiceImpl implements DiscountService{
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> getDiscounts() {
        return this.discountRepository.getDiscounts();
    }

    @Override
    public boolean addDiscount(Discount discount) {
        return this.discountRepository.addDiscount(discount);
    }

    @Override
    public boolean deleteDiscount(int discountId) {
        return this.discountRepository.deleteDiscount(discountId);
    }
    
}
