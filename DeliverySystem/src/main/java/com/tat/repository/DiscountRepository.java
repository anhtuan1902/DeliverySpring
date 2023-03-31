/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.repository;

import com.tat.pojos.Discount;
import java.util.List;

/**
 *
 * @author trant
 */
public interface DiscountRepository {
    List<Discount> getDiscounts();
}
