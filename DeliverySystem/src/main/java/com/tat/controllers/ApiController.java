/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Auction;
import com.tat.pojos.Comment;
import com.tat.pojos.Order1;
import com.tat.pojos.Rating;
import com.tat.service.AuctionService;
import com.tat.service.DiscountService;
import com.tat.service.PostService;
import com.tat.service.SendEmailService;
import com.tat.service.ShipperService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trant
 */
@RestController
public class ApiController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private PostService postService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ShipperService shipperService;
    
    @Autowired
    private SendEmailService sendEmailService;
   
    
    @DeleteMapping("/api/discounts/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable(value = "discountId") int id) {
        this.discountService.deleteDiscount(id);
    }

    @DeleteMapping("/api/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "postId") int id) {
        this.postService.deletePost(id);
    }

    @PostMapping(path = "/api/posts/{postId}/auctions", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Auction> addAuction(@RequestBody Map<String, String> params) {
        String content = params.get("content");
        double price = Double.parseDouble(params.get("price"));
        int postId = Integer.parseInt(params.get("postId"));

        Auction a = this.auctionService.addAuction(content, price, postId);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/posts/{auctionId}/auctions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuction(@PathVariable(value = "auctionId") int id) {
        this.auctionService.deleteAuction(id);
    }
    
    @PostMapping(path = "/api/order", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Order1> addOrder(@RequestBody Map<String, String> params) {
        int auctionId = Integer.parseInt(params.get("auctionId"));
        int shipperId = Integer.parseInt(params.get("shipperId"));
        String status = "CHƯA NHẬN HÀNG";

        Order1 o = this.auctionService.addOrder(status, auctionId, shipperId);
        
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }

    @PostMapping(path = "/api/shipper/{id}/rating", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Rating> addRate(@RequestBody Map<String, String> params, @PathVariable(value = "id") int id) {
        int rate = Integer.parseInt(params.get("rate"));

        Rating rating = this.shipperService.addOrUpdateRating(rate, id);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @PostMapping(path = "/api/shipper/{id}/comments", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> params) {
        String content = params.get("content");
        int id = Integer.parseInt(params.get("id"));

        Comment c = this.shipperService.addComment(content, id);

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/shipper/{commentId}/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(value = "commentId") int id) {
        this.shipperService.deleteComment(id);
    }

    @PatchMapping("/api/shipper/verified")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVerify(@RequestBody Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        this.shipperService.updateShipper(id);
    }
    
    @PostMapping("/api/sendemail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendEmail(@RequestBody Map<String, String> params){
        String userEmail = params.get("userEmail");
        String result = params.get("result");
        
        this.sendEmailService.sendEmail(userEmail, result);
    }
    
    @PatchMapping("/api/updateorder")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@RequestBody Map<String, String> params) {
        int orderId = Integer.parseInt(params.get("orderId"));
        String status = params.get("status");
        this.auctionService.updateOrder(orderId, status);
    }
}
