/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Auction;
import com.tat.pojos.Order1;
import com.tat.repository.AuctionRepository;
import com.tat.repository.PostRepository;
import com.tat.repository.ShipperRepository;
import com.tat.repository.UserRepository;
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
public class AuctionRepositoryImpl implements AuctionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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
        Query q = s.createQuery("SELECT COUNT(a.id) FROM Auction a");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Auction addAuction(String content, double price, int postId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Auction auction = new Auction();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        auction.setShipperId(this.shipperRepository.getShipperByUserId(this.userRepository.getUsers(authentication.getName()).get(0)));
        auction.setContent(content);
        auction.setPrice(price);
        auction.setPostId(this.postRepository.getPostById(postId));

        s.save(auction);
        return auction;
    }

    @Override
    public boolean deleteAuction(int auctionId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Auction a = s.get(Auction.class, auctionId);
        try {
            a.setActive(false);
            s.update(a);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public Order1 addOrder(String status, int auctionId, int shipperId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();

        Order1 o = new Order1();
        o.setStatusOrder(status);
        o.setAuctionId(this.getAuctionById(auctionId));
        o.setShipperId(this.shipperRepository.getShipperById(shipperId));
        s.save(o);

        return o;

    }

    @Override
    public Order1 getOrderByAuctionId(int auctionId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();

        Query q = s.createQuery("From Order1 o Where o.auctionId=:t");
        q.setParameter("t", s.get(Auction.class, auctionId));

        return (Order1) q.getSingleResult();
    }

    @Override
    public Auction getAuctionById(int auctionId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Auction.class, auctionId);
    }

    @Override
    public List<Order1> getOrders() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Order1 o WHERE o.active = :active");
        q.setParameter("active", true);

        return q.getResultList();
    }

    @Override
    public boolean updateOrder(int orderId, String status) {
        Session s = this.sessionFactory.getObject().getCurrentSession();       
        Order1 o = s.get(Order1.class, orderId);
        
        try {
            o.setStatusOrder(status);
            s.update(o);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

}
