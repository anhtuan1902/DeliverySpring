/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Comment;
import com.tat.pojos.Rating;
import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import com.tat.repository.CustomerRepository;
import com.tat.repository.ShipperRepository;
import com.tat.repository.UserRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ShipperRepositoryImpl implements ShipperRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Shipper> getShipper(Map<String, String> params) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Shipper> q = b.createQuery(Shipper.class);
        Root root = q.from(Shipper.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");

        if (kw != null && !kw.isEmpty()) {
            String p = String.format("%%%s%%", kw);
            Predicate p1 = b.like(root.get("name").as(String.class),
                    p);
            Predicate p2 = b.like(root.get("CMND").as(String.class),
                    p);
            q = q.where(b.or(p1, p2));

        }

        q.orderBy(b.desc(root.get("id")));
        List<Shipper> shippers = s.createQuery(q).getResultList();
        return shippers;
    }

    @Override
    public Shipper getShipperById(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Shipper.class, id);
    }

    @Override
    public boolean addShipper(Shipper shipper) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(shipper);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public Shipper getShipperByUserId(User userId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Shipper s Where s.userId=:t");
        q.setParameter("t", userId);
        return (Shipper) q.getSingleResult();
    }

    @Override
    public Double getStatisRating(int shipperId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT AVG(r.rate) From Rating r Where r.shipperId=:t");
        q.setParameter("t", this.getShipperById(shipperId));

        return (Double) q.getSingleResult();
    }

    @Override
    public Rating addOrUpdateRating(int rate, int shipperId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();

        if (!this.getShipperById(shipperId).getRatingSet().isEmpty()) {
            Rating rating = this.getRatingByShipperId(shipperId);
            DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = new Date();
            rating.setUpdatedDate(formatDate.format(today));
            rating.setRate(rate);
            s.update(rating);

            return rating;
        } else {
            Rating r = new Rating();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            r.setCreatorId(this.customerRepository.getCustomerByUserId(this.userRepository.getUsers(authentication.getName()).get(0)));
            r.setRate(rate);
            r.setShipperId(this.getShipperById(shipperId));
            s.save(r);
            return r;
        }
    }

    @Override
    public Rating getRatingByShipperId(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Query q = s.createQuery("From Rating r Where r.creatorId=:t and r.shipperId=:i");
        q.setParameter("i", this.getShipperById(id));
        q.setParameter("t", this.customerRepository.getCustomerByUserId(this.userRepository.getUsers(authentication.getName()).get(0)));

        return (Rating) q.getSingleResult();
    }

    @Override
    public List<Comment> getComments(int shipperId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Comment c Where c.shipperId=:t and c.active=:i");
        q.setParameter("t", this.getShipperById(shipperId));
        q.setParameter("i", true);

        return q.getResultList();
    }

    @Override
    public Comment addComment(String content, int shipperId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Comment c = new Comment();
        c.setContent(content);
        c.setShipperId(this.getShipperById(shipperId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        c.setCreatorId(this.customerRepository.getCustomerByUserId(this.userRepository.getUsers(authentication.getName()).get(0)));

        session.save(c);

        return c;
    }

    @Override
    public boolean deleteComment(int commentId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Comment c = s.get(Comment.class, commentId);
        try {
            c.setActive(false);
            s.update(c);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean updateShipper(int shipperId) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Shipper shipper = s.get(Shipper.class, shipperId);

        try {
            if (shipper.getAlreadyVerify()) {
                shipper.setAlreadyVerify(false);
            } else {
                shipper.setAlreadyVerify(true);
            }
            s.update(shipper);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }
}
