/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Discount;
import com.tat.repository.DiscountRepository;
import java.util.List;
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
public class DiscountRepositoryImpl implements DiscountRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Discount> getDiscounts() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Discount d " + "Where d.active=:t");
        q.setParameter("t", true);
        
        return q.getResultList();
    }

    @Override
    public boolean addDiscount(Discount discount) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(discount);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteDiscount(int discountId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Discount d = s.get(Discount.class, discountId);
        try {
            d.setActive(false);
            s.update(d);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }
}
