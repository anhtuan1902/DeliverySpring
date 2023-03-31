/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Customer;
import com.tat.pojos.User;
import com.tat.service.CustomerService;
import com.tat.service.ShipperService;
import com.tat.service.UserService;
import com.tat.validator.DeliveryWebValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author trant
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ShipperService shipperService;
    

    @Autowired
    private DeliveryWebValidator userValidator;

    @InitBinder(value = "form")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }
    
    @GetMapping(value = "/login")
    public String loginView(Model model) {

        return "login";
    }
    
    @GetMapping(value = "/signup")
    public String registerViewCustomer(Model model) {
        User user = new User();
        Customer customer = new Customer();
        model.addAttribute("user", user);
        model.addAttribute("customer", customer);
        
        return "signupcustomer";
    }
    
    @PostMapping(value = "/signup")
    public String registerProcessCustomer(Model model, @ModelAttribute(name = "user") @Valid User user, 
            @ModelAttribute(name = "customer") @Valid Customer c,
            BindingResult result) {
        if (result.hasErrors()) {
            return "signupcustomer";
        }

        user.setUserRole(User.CUSTOMER);

        if (this.userService.addUser(user) == true) {
            c.setUserId(user);
            this.customerService.addCustomer(c);
            return "redirect:/login";
        }else {
            model.addAttribute("errMsg", "Something wrong!!!");
        }
        return "signupcustomer";
    }
    
//    @PostMapping(value = "/shipper/register")
//    public String registerShipper(Model model, @ModelAttribute(name = "user") @Valid User user, 
//            @ModelAttribute(name = "shipper") @Valid Shipper shipper,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            return "registershipper";
//        }
//
//        user.setUserRole(User.SHIPPER);
//
//        if (this.userService.addUser(user) == true) {
//            shipper.setUserId(user);
//            this.shipperService.addShipper(shipper);
//            return "redirect:/login";
//        }else {
//            model.addAttribute("errMsg", "Something wrong!!!");
//        }
//        return "registershipper";
//    }

}
