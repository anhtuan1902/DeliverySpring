/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tat.pojos.Shipper;
import com.tat.repository.ShipperRepository;
import com.tat.service.ShipperService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addShipper(Shipper s) {
        if (!s.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(s.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                s.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ShipperServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.shipperRepository.addShipper(s);
    }

    @Override
    public Shipper getShipperByUserId(int userId) {
        return this.shipperRepository.getShipperById(userId);
    }

}
