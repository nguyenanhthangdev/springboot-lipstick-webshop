package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByEmail(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

    @Query("SELECT m FROM Manufacturer m WHERE m.name LIKE %:keyword%")
    List<User> searchUserByName(String keyword);

    Page<User> findByUsernameContaining(String username, Pageable pageable);

    Page<User> findByStatusAndUsernameContaining(Boolean status, String username, Pageable pageable);

//    @Query("")
//    boolean checkIfTheOldPasswordIsCorrect(String username, String hashedPassword);
}

