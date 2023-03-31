/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.repository;

import com.tat.pojos.Shipper;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public interface ShipperRepository {
    List<Shipper> getShipper(Map<String, String> params);
    Shipper getShipperById(int id);
    Shipper getShipperByUserId(int userId);
    void addShipper(Shipper s);
}
