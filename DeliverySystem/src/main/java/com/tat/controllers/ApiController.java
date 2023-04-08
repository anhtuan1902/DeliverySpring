/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Auction;
import com.tat.service.AuctionService;
import com.tat.service.DiscountService;
import com.tat.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @DeleteMapping("/api/discounts/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable(value = "discountId") int id){
        this.discountService.deleteDiscount(id);
    }
    
    @DeleteMapping("/api/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "postId") int id){
        this.postService.deletePost(id);
    }
    
    @PostMapping(path = "/api/posts/addAuction", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addAuction(@RequestBody Map<String, String> params) {
        String content = params.get("content");
        double price = Double.parseDouble(params.get("price"));
        int postId = Integer.parseInt(params.get("postId"));
        
        this.auctionService.addAuction(content, price, postId);
    }
}
