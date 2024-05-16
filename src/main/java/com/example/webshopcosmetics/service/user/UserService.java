package com.example.webshopcosmetics.service.user;

import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> searchUserByUserName(String keyword);

    public Page<User> getAllUser(int pageNoString, String keyword, int size, int status);

    List<User> getAllUser();

    public User saveUser(User user);

    public User updateUser(User user);

    public User getOneUserById(Long id);

    public User findByUsername(String username);

//    public boolean checkUserNameExists(String username);

    public boolean checkIfTheOldPasswordIsCorrect(String username, String password);

    public void deleteUserById(Long id);

    public User findUserById(Long user_id);
}
