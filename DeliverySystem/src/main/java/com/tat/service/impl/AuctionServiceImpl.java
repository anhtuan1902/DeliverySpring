/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.tat.pojos.Auction;
import com.tat.pojos.Order1;
import com.tat.repository.AuctionRepository;
import com.tat.service.AuctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRepository auctionRepository;
    
    @Override
    public List<Auction> getAuctions() {
        return this.auctionRepository.getAuctions();
    }

    @Override
    public int countAuction() {
        return this.auctionRepository.countAuction();
    }

    @Override
    public Auction addAuction(String content, double price, int postId) {
        return this.auctionRepository.addAuction(content, price, postId);
    }

    @Override
    public boolean deleteAuction(int auctionId) {
        return this.auctionRepository.deleteAuction(auctionId);
    }

    @Override
    public Order1 getOrderByAuctionId(int autionId) {
        return this.auctionRepository.getOrderByAuctionId(autionId);
    }

    @Override
    public Order1 addOrder(String status, int auctionId, int shipperId) {
        return this.auctionRepository.addOrder(status, auctionId, shipperId);
    }

    @Override
    public List<Order1> getOrders() {
        return this.auctionRepository.getOrders();
    }

    @Override
    public boolean updateOrder(int orderId, String status) {
        return this.auctionRepository.updateOrder(orderId, status);
    }
    
}
