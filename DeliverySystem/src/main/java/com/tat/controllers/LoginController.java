/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.controllers;

import com.tat.pojos.Customer;
import com.tat.pojos.Shipper;
import com.tat.pojos.User;
import com.tat.service.CustomerService;
import com.tat.service.ShipperService;
import com.tat.service.UserService;
import com.tat.validator.DeliveryWebValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registerView(ModelMap model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping(value = "/signup")
    public String registerProcess(Model model, @ModelAttribute(name = "user") @Valid User user,
            BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }

        if (this.userService.addUser(user) == true) {
            User u = this.userService.getUserByUsername(user.getUsername());
            if ("CUSTOMER_ROLE".equals(u.getUserRole())) {
                Customer c = new Customer();
                c.setFile(user.getFile());
                c.setUserId(u);
                if (this.customerService.addCustomer(c) == true) {
                    return "redirect:/login";
                } else {
                    model.addAttribute("errMsg", "Thông tin bị lỗi!!!");
                }
            } else if ("SHIPPER_ROLE".equals(u.getUserRole())) {
                Shipper s = new Shipper();
                s.setCmnd(user.getCMND());
                s.setFile(user.getFile());
                s.setUserId(u);
                if (this.shipperService.addShipper(s) == true) {
                    return "redirect:/login";
                } else {
                    model.addAttribute("errMsg", "Thông tin bị lỗi!!!");
                }
            }
        } else {
            model.addAttribute("errMsg", "Thông tin bị lỗi!!!");
        }
        return "signup";
    }

//    @GetMapping(value = "/customerform")
//    public String customerFormView(ModelMap model) {
//        Customer c = new Customer();
//        model.addAttribute("customer", c);
//       
//        return "customerforminfo";
//    }
//    
//    @PostMapping(value = "/customerform")
//    public String customerFormProcess(Model model, @ModelAttribute(name = "customer") @Valid Customer c,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            return "customerforminfo";
//        }
//        if (this.customerService.addCustomer(c) == true) {
//            
//            return "redirect:/login";
//        }else {
//            model.addAttribute("errMsg", "Thông tin bị lỗi!!!");
//        }
//        return "customerforminfo";
//    }   
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
