/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tat.pojos.Comment;
import com.tat.pojos.Rating;
import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import com.tat.repository.ShipperRepository;
import com.tat.service.ShipperService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Shipper> getShippers(Map<String, String> params) {
        return this.shipperRepository.getShipper(params);
    }

    @Override
    public Shipper getShipperById(int id) {
        return this.shipperRepository.getShipperById(id);
    }

    @Override
    public boolean addShipper(Shipper s) {
        if (!s.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(s.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                s.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ShipperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.shipperRepository.addShipper(s);
    }

    @Override
    public Shipper getShipperByUserId(User userId) {
        return this.shipperRepository.getShipperByUserId(userId);
    }

    @Override
    public Double getStatisRating(int shipperId) {
        return this.shipperRepository.getStatisRating(shipperId);
    }

    @Override
    public Rating addOrUpdateRating(int rate, int shipperId) {
        return this.shipperRepository.addOrUpdateRating(rate, shipperId);
    }

    @Override
    public Rating getRatingByShipperId(int id) {
        return this.shipperRepository.getRatingByShipperId(id);
    }

    @Override
    public List<Comment> getComments(int shipperId) {
        return this.shipperRepository.getComments(shipperId);
    }

    @Override
    public Comment addComment(String content, int shipperId) {
        return this.shipperRepository.addComment(content, shipperId);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return this.shipperRepository.deleteComment(commentId);
    }

    @Override
    public boolean updateShipper(int shipperId) {
        return this.shipperRepository.updateShipper(shipperId);
    }

}
