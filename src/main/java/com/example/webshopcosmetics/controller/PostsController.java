package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.PostsException;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Posts;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.posts.PostsService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class PostsController {
    @Autowired private PostsService postsService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/admin/posts")
    public String getAllPosts(Model model) {
        model.addAttribute("active_posts", "ACTIVE");
        model.addAttribute("posts", postsService.getAllPosts());
        return "admin/posts/all-posts";
    }

    @GetMapping("/admin/posts/add-posts")
    public String addPosts(Model model) {
        model.addAttribute("active_posts", "ACTIVE");
        return "admin/posts/add-posts";
    }

    @PostMapping("/admin/posts/add-post")
    public String savePosts(@RequestParam(value = "image") String image, @RequestParam("status") boolean status,
                            @RequestParam("postsTitle") String postsTitle, @RequestParam("postsDetails") String postsDetail,
                            RedirectAttributes redirectAttributes) {
        try {
            Posts posts = new Posts();
            posts.setImage(image);
            posts.setPostsTitle(postsTitle);
            posts.setPostsDetails(postsDetail);
            posts.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            posts.setCreatedAt(currentDateTime);
            postsService.savePosts(posts);
            String successMessage = "Thêm bài viết thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/posts";
        } catch (PostsException e) {
            String errorMessage = "Thêm bài viết không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/posts";
        }
    }

    @GetMapping("/admin/posts/edit-posts")
    public String editPosts(Model model, @Param(value = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("posts", postsService.getOnePosts(id));
            model.addAttribute("active_posts", "ACTIVE");
            return "admin/posts/edit-posts";
        } catch (PostsException e){
            String errorMessage = "Chỉnh sửa bài viết không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/posts";
        }
    }

    @PostMapping("/admin/posts/edit-post")
    public String updatePosts(@RequestParam(value = "image") String image, @RequestParam("status") boolean status,
                            @RequestParam("postsTitle") String postsTitle, @RequestParam("postsDetails") String postsDetail,
                            RedirectAttributes redirectAttributes, @RequestParam(value = "posts_id") Long posts_id) {
        try {
            Posts posts = postsService.getOnePosts(posts_id);
            posts.setImage(image);
            posts.setPostsTitle(postsTitle);
            posts.setPostsDetails(postsDetail);
            posts.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            posts.setUpdatedAt(currentDateTime);
            postsService.savePosts(posts);
            String successMessage = "Chỉnh sửa bài viết thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/posts";
        } catch (PostsException e) {
            String errorMessage = "Chỉnh sửa bài viết không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/posts";
        }
    }

    @GetMapping("/admin/posts/delete-posts")
    public String deletePosts(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            postsService.deletePosts(id);
            String successMessage = "Xóa bài viết thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/posts";
        } catch (PostsException e) {
            String errorMessage = "Xóa bài viết không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/posts";
        }
    }

    @GetMapping("/cosmetic/post/")
    public String deletePostById(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            postsService.deletePosts(id);
            String successMessage = "Xóa bài viết thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/posts";
        } catch (PostsException e) {
            String errorMessage = "Xóa bài viết không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/posts";
        }
    }

    @GetMapping("/cosmetic/posts-details")
    public String getPostByIdHome(@Param("id") Long id,Model model, RedirectAttributes redirectAttributes) {
        try {
            Posts posts = postsService.getOnePosts(id);
            model.addAttribute("posts", posts);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "client/home/posts-details";
        } catch (PostsException e) {
            String errorMessage = "Đã có lỗi xảy ra";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/cosmetic";
        }
    }
}
