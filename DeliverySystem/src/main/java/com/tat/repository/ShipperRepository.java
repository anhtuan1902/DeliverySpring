/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.repository;

import com.tat.pojos.Comment;
import com.tat.pojos.Rating;
import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public interface ShipperRepository {
    List<Shipper> getShipper(Map<String, String> params);
    Shipper getShipperById(int id);
    Shipper getShipperByUserId(User userId);
    boolean addShipper(Shipper s);
    boolean updateShipper(int shipperId);
    Rating getRatingByShipperId(int id);
    Double getStatisRating(int shipperId);
    Rating addOrUpdateRating(int rate, int shipperId);
    List<Comment> getComments(int shipperId);
    Comment addComment(String content, int shipperId);
    boolean deleteComment(int commentId);
}
