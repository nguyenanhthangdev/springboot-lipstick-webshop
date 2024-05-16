package com.example.webshopcosmetics.service.user;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.UserException;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> searchUserByUserName(String keyword) {
        return userRepository.searchUserByName(keyword);
    }

    @Override
    public Page<User> getAllUser(int pageNo, String keyword, int size, int status) {
        if (keyword==null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }
        if (userRepository.count() <= size) {
            pageNo = 1;
        }

        Pageable pageable = PageRequest.of(pageNo - 1, size);
        if (status == 2) {
            // Trường hợp status=null, không lọc theo status
            return userRepository.findByUsernameContaining(keyword, pageable);
        } else {
            boolean boolStatus = false;
            if (status == 1) {
                boolStatus = true;
            }
            // Trường hợp status=true hoặc status=false
            return userRepository.findByStatusAndUsernameContaining(boolStatus, keyword, pageable);
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserException("Thêm nhân viên không thành công thành công", e);
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserException("Thay đổi thông tin nhân viên không thành công thành công", e);
        }
    }

    @Override
    public User getOneUserById(Long id) {
        try {
            return userRepository.getOne(id);
        } catch (Exception e) {
            throw new UserException("Nhân viên không tồn tại", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean checkIfTheOldPasswordIsCorrect(String username, String password) {
        User user = userRepository.findByUsername(username);

        // Kiểm tra xem người dùng có tồn tại không
        if(user != null) {
            // Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
            String hashedPasswordFromDatabase = user.getPassword();

            // So sánh mật khẩu đã nhập với mật khẩu đã mã hóa từ cơ sở dữ liệu
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password, hashedPasswordFromDatabase);
        }
        return false;
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserException("Xóa nhân viên không thành công", e);
        }
    }

    @Override
    public User findUserById(Long user_id) {
        return userRepository.getById(user_id);
    }
}
