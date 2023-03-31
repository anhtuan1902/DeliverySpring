/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Customer;
import com.tat.repository.CustomerRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trant
 */
@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void addCustomer(Customer c) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.save(c);
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.getReference(Customer.class, userId);
    }

}
