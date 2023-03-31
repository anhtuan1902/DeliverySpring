/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.service;

import com.tat.pojos.Customer;

/**
 *
 * @author trant
 */
public interface CustomerService {
    void addCustomer(Customer c);
    Customer getCustomerByUserId(int userId);
}
