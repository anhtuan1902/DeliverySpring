/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.repository;

import com.tat.pojos.Admin;
import com.tat.pojos.User;

/**
 *
 * @author trant
 */
public interface AdminRepository {
    Admin getAdminByUserId(User userId);
}
