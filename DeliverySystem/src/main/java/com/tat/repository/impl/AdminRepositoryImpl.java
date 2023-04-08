/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Admin;
import com.tat.pojos.User;
import com.tat.repository.AdminRepository;
import javax.persistence.Query;
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
public class AdminRepositoryImpl implements AdminRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public Admin getAdminByUserId(User userId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Admin a " + "Where a.userId=:t");
        q.setParameter("t", userId);
        return (Admin) q.getSingleResult();
    }

}
