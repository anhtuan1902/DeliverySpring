/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Discount;
import com.tat.pojos.User;
import com.tat.service.AdminService;
import com.tat.service.DiscountService;
import com.tat.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author trant
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminService adminService;

    @ModelAttribute
    public void commonAttributes(Model model, Principal principal) {
        model.addAttribute("discounts", this.discountService.getDiscounts());

        String username = principal.getName();
        User u = this.userService.getUsers(username);
        model.addAttribute("userinfo", u);

    }

    @RequestMapping("/discounts")
    public String addDiscount(Model model, @ModelAttribute(value = "discount") @Valid Discount discount, BindingResult rs, Principal principal) {
        if (rs.hasErrors()) {
            return "discounts";
        }
        
        discount.setAdminId(this.adminService.getAdminByUserId(this.userService.getUsers(principal.getName())));
        if (this.discountService.addDiscount(discount)) {
            return "redirect:/admin/discounts";
        } else {
            model.addAttribute("errMsg", "Something wrong!!!");
        }

        return "discounts";
    }

    @GetMapping("/discounts")
    public String discounts(Model model) {
        model.addAttribute("discount", new Discount());
        return "discounts";
    }
    
    
}
