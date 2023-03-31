/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Auction;
import com.tat.pojos.Discount;
import com.tat.pojos.Post;
import com.tat.pojos.Shipper;
import com.tat.service.AuctionService;
import com.tat.service.DiscountService;
import com.tat.service.PostService;
import com.tat.service.ShipperService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trant
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private ShipperService shipperService;

    @Autowired
    private PostService postService;

    @Autowired
    private DiscountService discountService;
    
    @Autowired
    private AuctionService auctionService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        
        List<Discount> discounts = this.discountService.getDiscounts();
        model.addAttribute("discounts", discounts);
        
        List<Auction> auctions = this.auctionService.getAuctions();
        model.addAttribute("auctions", auctions);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Post> posts = this.postService.getPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());

        return "home";
    }

    @RequestMapping(path = {"/addPost"}, method = RequestMethod.POST)
    public String addPost(Model model, @ModelAttribute(value = "post") @Valid Post post,
            BindingResult rs) {
        if (rs.hasErrors()) {
            List<Post> posts = this.postService.getPosts();
            model.addAttribute("posts", posts);
            return "home";
        }

        if (this.postService.addOrUpdatePost(post) == true) {
            return "redirect:/";
        } else {
            model.addAttribute("errMsg", "Something wrong!!!");
        }

        return "home";
    }
    
    @GetMapping(path = "/shipper")
    public String shipperpage(Model model, @RequestParam Map<String, String> params){
        List<Shipper> shippers = this.shipperService.getShippers(params);
        model.addAttribute("shippers", shippers);
        
        return "shipperpage";
    }
    
    @GetMapping(path = "/shipper/{id}")
    public String details(Model model, 
            @PathVariable(value = "id") int id) {
        model.addAttribute("shipper", this.shipperService.getShipperById(id));
        return "shipperdetailpage";
    }

}
