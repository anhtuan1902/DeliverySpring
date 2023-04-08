/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import com.tat.repository.ShipperRepository;
import java.util.ArrayList;
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
        Query q = s.createQuery("From Shipper s " + "Where s.userId=:t");
        q.setParameter("t", userId);
        return (Shipper) q.getSingleResult();
    }
}
