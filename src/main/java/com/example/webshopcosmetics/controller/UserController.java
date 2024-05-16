package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.UserDTO;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.exception.UserException;
import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String getAllUser(Model model, @Param("keyword") String keyword, @RequestParam(name="size", defaultValue = "10") String sizeString,
                             @RequestParam(name="pageNo", defaultValue = "1") String pageNoString, @RequestParam(value = "status", defaultValue = "2") String statusString) {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoString);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        int status = 2;
        try {
            status = Integer.parseInt(statusString);
        } catch (NumberFormatException e) {
            status = 2;
        }
        int size = 10;
        try {
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException e) {
            size = 10;
        }
        Page<User> users = userService.getAllUser(pageNo, keyword, size, status);
        model.addAttribute("active_user", "ACTIVE");
        model.addAttribute("users", users);
        model.addAttribute("totalPage", users.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("status", status);
        return "admin/user/all-user";
    }

    @GetMapping("/admin/user/add-user")
    public String addUser(Model model) {
        model.addAttribute("active_user", "ACTIVE");
        model.addAttribute("user", new User());
        return "admin/user/add-user";
    }

    @PostMapping("/admin/user/add-user")
    public String saveUser(RedirectAttributes redirectAttributes, @RequestParam(value = "image") String image,
                           @RequestParam("fullname") String fullname, @RequestParam("username") String username,
                           @RequestParam("email") String email, @RequestParam("password") String password,
                           @RequestParam("status") boolean status, @RequestParam("phone") String phone) {

        if (userService.findByUsername(username) != null){
            redirectAttributes.addFlashAttribute("errorMessage", "Tên đăng nhập đã tồn tại, vui lòng thử lại");
            return "redirect:/admin/user/add-user";
        }
        try {
//            String uniqueString = UUID.randomUUID().toString();
//            String fileName = uniqueString + "-" + image.getOriginalFilename();
//            byte[] bytes = image.getBytes();
//            Path path = Paths.get("src/main/resources/static/images/user/" + fileName);
//            Files.write(path, bytes);

            User user = new User();
            user.setImage(image);
            user.setFullname(fullname.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setUsername(username.trim());
            user.setPassword(password.trim());
            user.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            user.setCreatedAt(currentDateTime);
            user.setUpdatedAt(currentDateTime);
            userService.saveUser(user);
            String successMessage = "Thêm nhân viên thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/user";
        } catch (UserException e) {
            String errorMessage = "Thêm nhân viên không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/admin/user/edit-user")
    public String editUser(Model model, @Param("id") Long id) {
        try {
            model.addAttribute("active_user", "ACTIVE");
            model.addAttribute("user", userService.getOneUserById(id));
            return "admin/user/edit-user";
        } catch (UserException e) {
            String errorMessage = "Nhân viên không tồn tại";
            model.addAttribute("errorMessage", errorMessage);
            return "admin/user";
        }
    }

    @PostMapping("/admin/user/edit-user")
    public String updateUser(@RequestParam(value = "id") long id, @RequestParam(value = "image") String image,
                             @RequestParam("status") boolean status, @RequestParam("fullname") String fullname,
                             @RequestParam("email") String email, @RequestParam("phone") String phone, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getOneUserById(id);
            user.setImage(image);
            user.setFullname(fullname.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            user.setUpdatedAt(currentDateTime);
            userService.saveUser(user);
            String successMessage = "Thay đổi thông tin nhân viên thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/user";
        } catch (UserException e) {
            String errorMessage = "Thay đổi thông tin nhân viên không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/admin/user/change-password")
    public String changePassword(Model model) {
        return "admin/user/change-password";
    }

    @PostMapping("/admin/user/change-password")
    public String saveChangePassword(@RequestParam("password_old") String password_old,
                                     @RequestParam("password_new") String password_new,
                                     @RequestParam("comfirm_password_new") String comfirm_password_new,
                                     HttpSession session, RedirectAttributes redirectAttributes) {
        UserDTO userDTO = (UserDTO) session.getAttribute("s_user");
        User user = userService.findByUsername(userDTO.username());
        if (user != null) {
            user.setPassword(password_new);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Thay đổi mật khẩu thành công");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Thay đổi mật khẩu không thành công");
        }
        return "redirect:/admin/user/change-password";
    }

    @PostMapping("/admin/user/checkPasswordOld")
    @ResponseBody
    public String checkIfTheOldPasswordIsCorrect(@RequestParam("passwordOld") String passwordOld, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("s_user");
        if (userDTO == null) {
            // Không tìm thấy thông tin người dùng trong session, có thể do chưa đăng nhập
            return "error"; // Chuyển hướng đến trang đăng nhập
        } else {
            if (!userService.checkIfTheOldPasswordIsCorrect(userDTO.username(), passwordOld)) {
                // Mật khẩu cũ không đúng, chuyển hướng người dùng đến trang đổi mật khẩu
                return "false";
            } else {
                // Mật khẩu cũ đúng, trả về kết quả true
                return "true";
            }
        }
    }

    @GetMapping("/admin/user/delete-user")
    public String deleteUser(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getOneUserById(id);
            userService.deleteUserById(id);
            String successMessage = "Xóa nhân viên thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/user";
        } catch (UserException e) {
            String errorMessage = "Xóa nhân viên không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/admin/user/profile-user")
    public String profileUser(Model model, HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("s_user");
        User user = userService.findUserById(userDTO.id());
        if (user != null) {
            model.addAttribute("profile", user);
            return "admin/user/profile-user";
        } else {
            model.addAttribute("errorMessage", "Xem thông tin nhân viên không thành công!");
            return "admin/dashboard/dashboard";
        }
    }

    @PostMapping("/admin/user/profile-user")
    public String saveprofileUser(@RequestParam("id") long id, @RequestParam("fullname") String fullname, HttpSession session,
                                  @RequestParam(value = "image") String image, @RequestParam("email") String email,
                                  @RequestParam("phone") String phone, RedirectAttributes redirectAttributes, @RequestParam("username") String username) {
        try {
            User user = userService.getOneUserById(id);
            user.setFullname(fullname.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setUsername(username);
            user.setImage(image);

            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Thay đổi thông tin nhân viên thành công");
            UserDTO userDTO = (UserDTO) session.getAttribute("s_user");
            if (userDTO != null) {
                session.removeAttribute("s_user");
            }
            session.setAttribute("s_user", new UserDTO(id, fullname, username, image));
            redirectAttributes.addFlashAttribute("successMessage", "Thay đổi thông tin nhân viên thành công");
            return "redirect:/admin/user/profile-user";
        } catch (UserException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thay đổi thông tin nhân viên không thành công");
            return "redirect:/admin/user/profile-user";
        }
    }
}
