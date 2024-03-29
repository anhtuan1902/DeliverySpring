/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.pojos.Post;
import com.tat.repository.PostRepository;
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
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Post> getPosts() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Post p " + "Where p.active=:t");

        q.setParameter("t", true);
        return q.getResultList();
    }

    @Override
    public boolean addPost(Post post) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            s.save(post);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deletePost(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Post p = s.get(Post.class, id);
        try {
            p.setActive(false);
            s.update(p);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public Post getPostById(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Post.class, id);
    }

}
