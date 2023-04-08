/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.service;

import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public interface ShipperService {
    List<Shipper> getShippers(Map<String, String> params);
    Shipper getShipperById(int id);
    Shipper getShipperByUserId(User userId);
    boolean addShipper(Shipper s);
}
