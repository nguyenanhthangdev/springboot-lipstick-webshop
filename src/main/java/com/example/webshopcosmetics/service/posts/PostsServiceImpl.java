package com.example.webshopcosmetics.service.posts;

import com.example.webshopcosmetics.exception.ContactException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.PostsException;
import com.example.webshopcosmetics.model.Posts;
import com.example.webshopcosmetics.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired private PostsRepository postsRepository;

    @Override
    public Posts savePosts(Posts posts) {
        try {
            return postsRepository.save(posts);
        } catch (Exception e) {
            throw new PostsException("Thêm bài viết thất bại", e);
        }
    }

    @Override
    public List<Posts> getAllPosts() {
        try {
            return postsRepository.findAllByOrderByCreatedAtDesc();
        } catch (Exception e) {
            throw new PostsException("Thao tác thất bại", e);
        }
    }

    @Override
    public Posts getOnePosts(Long id) {
        try {
            return postsRepository.getOne(id);
        } catch (Exception e) {
            throw new PostsException("Xem bài viết thất bại", e);
        }
    }

    @Override
    public void deletePosts(Long id) {
        try {
            postsRepository.deleteById(id);
        } catch (Exception e) {
            throw new PostsException("Xóa bài viết không thành công", e);
        };
    }

    @Override
    public List<Posts> getAllActivePosts() {
        return postsRepository.findAllActivePosts();
    }
}
