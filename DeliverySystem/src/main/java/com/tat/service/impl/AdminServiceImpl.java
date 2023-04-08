/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.cloudinary.Cloudinary;
import com.tat.pojos.Admin;
import com.tat.pojos.User;
import com.tat.repository.AdminRepository;
import com.tat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public Admin getAdminByUserId(User userId) {
        return this.adminRepository.getAdminByUserId(userId);
    }
    
}
