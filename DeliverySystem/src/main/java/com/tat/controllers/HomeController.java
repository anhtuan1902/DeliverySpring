/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Post;
import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import com.tat.service.AdminService;
import com.tat.service.AuctionService;
import com.tat.service.CustomerService;
import com.tat.service.DiscountService;
import com.tat.service.PostService;
import com.tat.service.ShipperService;
import com.tat.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trant
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ShipperService shipperService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PostService postService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private AuctionService auctionService;
    

    @ModelAttribute
    public void commonAttributes(Model model, Principal principal) {
        model.addAttribute("discounts", this.discountService.getDiscounts());

        model.addAttribute("auctions", this.auctionService.getAuctions());
        
        
        
        User u = this.userService.getUserByUsername(principal.getName());
        model.addAttribute("userinfo", u);
        if (u.getUserRole().contains("SHIPPER_ROLE")) {
            model.addAttribute("extrainfo", this.shipperService.getShipperByUserId(u));
        } else if (u.getUserRole().contains("CUSTOMER_ROLE")) {
            model.addAttribute("extrainfo", this.customerService.getCustomerByUserId(u));
        } else {
            model.addAttribute("extrainfo", this.adminService.getAdminByUserId(u));
        }
    }

    @RequestMapping(value = "/posts")
    public String postProcess(Model model, @ModelAttribute(value = "poster") @Valid Post post,
            BindingResult rs, Principal principal) {
        if (rs.hasErrors()) {
            return "home";
        }
        
        post.setDiscountId(this.discountService.getDiscounts().get(post.getDisId()));
        post.setCustomerId(this.customerService.getCustomerByUserId(this.userService.getUserByUsername(principal.getName())));
        if (this.postService.addPost(post)) {
            return "redirect:/home/posts";
        } else {
            model.addAttribute("errMsg", "Something wrong!!!");
        }
        return "home";
    }

    @GetMapping("/posts")
    public String postView(Model model) {
        model.addAttribute("posts", this.postService.getPosts());
        model.addAttribute("poster", new Post());
        model.addAttribute("totalAuction", this.auctionService.countAuction());
        
        return "home";
    }

    @GetMapping(path = "/shipper")
    public String shipperpage(Model model, @RequestParam Map<String, String> params) {
        List<Shipper> shippers = this.shipperService.getShippers(params);
        model.addAttribute("shippers", shippers);
        Shipper s = new  Shipper();

        return "shipperpage";
    }

    @GetMapping(path = "/shipper/{id}")
    public String details(Model model, @PathVariable(value = "id") int id, Principal principal) {
        model.addAttribute("shipper", this.shipperService.getShipperById(id));
        model.addAttribute("statisRate", this.shipperService.getStatisRating(id));
        if("CUSTOMER_ROLE".equals(this.userService.getUserByUsername(principal.getName()).getUserRole())){
            model.addAttribute("rate", this.shipperService.getRatingByShipperId(id));
        }
        model.addAttribute("comments", this.shipperService.getComments(id));
        
        return "shipperdetailpage";
    }
    
    @GetMapping("/shipper/orders")
    public String getViewOrder(Model model){
        model.addAttribute("orders", this.auctionService.getOrders());
        return "orderpage";
    }
}
