/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.cloudinary.Cloudinary;
import com.tat.pojos.Customer;
import com.tat.pojos.User;
import com.tat.repository.CustomerRepository;
import com.tat.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public boolean addCustomer(Customer c) {
        c.setAvatar("https://res.cloudinary.com/dzk2a3akv/image/upload/v1679593418/ImageSpring/avatar-default_ptjnjt.png");
        return this.customerRepository.addCustomer(c);
    }

    @Override
    public Customer getCustomerByUserId(User userId) {
        return this.customerRepository.getCustomerByUserId(userId);
    }

}
