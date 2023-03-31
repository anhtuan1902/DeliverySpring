/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Auction;
import com.tat.repository.AuctionRepository;
import java.util.List;
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
public class AuctionRepositoryImpl implements AuctionRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Auction> getAuctions() {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Auction a " + "Where a.active=:t");
        q.setParameter("t", true);
        
        return q.getResultList();
    }
    
}
