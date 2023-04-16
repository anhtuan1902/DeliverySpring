/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.service;

import com.tat.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author trant
 */
public interface UserService extends UserDetailsService{
    boolean addUser(User user);
    User getUserByUsername(String username);
}
