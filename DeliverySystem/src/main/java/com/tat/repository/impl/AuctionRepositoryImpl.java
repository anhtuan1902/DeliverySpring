/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Auction;
import com.tat.repository.AuctionRepository;
import com.tat.service.PostService;
import com.tat.service.ShipperService;
import com.tat.service.UserService;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @Autowired
    private ShipperService shipperService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @Override
    public List<Auction> getAuctions() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Auction a Where a.active=:t");
        q.setParameter("t", true);
        
        return q.getResultList();
    }

    @Override
    public int countAuction() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Auction");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Auction addAuction(String content, double price, int postId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Auction auction = new Auction();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        auction.setShipperId(this.shipperService.getShipperByUserId(this.userService.getUsers(authentication.getName())));
        auction.setContent(content);
        auction.setPrice(price);
        auction.setPostId(this.postService.getPostById(postId));
        
        s.save(auction);
        return auction;
    }
    
}
