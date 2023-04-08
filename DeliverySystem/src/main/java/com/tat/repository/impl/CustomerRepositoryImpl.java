/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Customer;
import com.tat.pojos.User;
import com.tat.repository.CustomerRepository;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
    public boolean addCustomer(Customer customer) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(customer);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public Customer getCustomerByUserId(User userId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Customer c Where c.userId=:t");
        q.setParameter("t", userId);
        return (Customer) q.getSingleResult();
    }

}
