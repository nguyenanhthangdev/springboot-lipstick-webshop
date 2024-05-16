package com.example.webshopcosmetics.service.posts;

import com.example.webshopcosmetics.model.Contact;
import com.example.webshopcosmetics.model.Posts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostsService {
    public Posts savePosts(Posts posts);

    public List<Posts> getAllPosts();

    public Posts getOnePosts(Long id);

    public void deletePosts(Long id);

    public List<Posts> getAllActivePosts();
}
