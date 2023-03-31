/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tat.service;

import com.tat.pojos.Post;
import java.util.List;

/**
 *
 * @author trant
 */
public interface PostService {
    List<Post> getPosts();
    boolean addOrUpdatePost(Post p);
}
