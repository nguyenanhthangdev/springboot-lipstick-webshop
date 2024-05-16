package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Posts;
import com.example.webshopcosmetics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    public List<Posts> findAllByOrderByCreatedAtDesc();

    @Query("SELECT p FROM Posts p WHERE p.status = true")
    List<Posts> findAllActivePosts();
}
