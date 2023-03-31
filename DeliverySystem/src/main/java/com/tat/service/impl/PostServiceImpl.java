/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tat.pojos.Post;
import com.tat.repository.PostRepository;
import com.tat.service.PostService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Post> getPosts() {
        return this.postRepository.getPosts();
    }

    @Override
    public boolean addOrUpdatePost(Post p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                p.setProductImg(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (p.getCreatedDate() != null && p.getUpdatedDate() != null){
            p.setUpdatedDate(new Date());
        } else {
            p.setCreatedDate(new Date());
            p.setUpdatedDate(new Date());
        }
        
        return this.postRepository.addOrUpdatePost(p);
    }

}
