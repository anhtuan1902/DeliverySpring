/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.repository;

import com.tat.pojos.Auction;
import com.tat.pojos.Order1;
import java.util.List;

/**
 *
 * @author trant
 */
public interface AuctionRepository {
    List<Auction> getAuctions();
    Auction getAuctionById(int auctionId);
    int countAuction();
    Auction addAuction(String content, double price, int postId);
    boolean deleteAuction(int auctionId);
    List<Order1> getOrders();
    Order1 getOrderByAuctionId(int autionId);
    Order1 addOrder(String status, int auctionId, int shipperId);
    boolean updateOrder(int orderId, String status);
}
