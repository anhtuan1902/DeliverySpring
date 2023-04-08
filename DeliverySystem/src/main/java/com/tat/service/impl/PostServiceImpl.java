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
    public boolean addPost(Post post) {
        if (!post.getFile().isEmpty()) {
            
            try {
                Map res = this.cloudinary.uploader().upload(post.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                post.setProductImg(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.postRepository.addPost(post);
    }

    @Override
    public boolean deletePost(int id) {
        return this.postRepository.deletePost(id);
    }

    @Override
    public Post getPostById(int id) {
        return this.postRepository.getPostById(id);
    }
}
