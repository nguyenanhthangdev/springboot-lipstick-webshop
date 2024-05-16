package com.example.webshopcosmetics.service.user;

import com.example.webshopcosmetics.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Ở đây, bạn cần trả về danh sách các quyền của người dùng
        // Ví dụ: nếu có một quyền ROLE_USER, bạn có thể trả về Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        // Nếu có nhiều quyền, bạn có thể trả về danh sách đầy đủ
        // Nếu không có quyền, bạn có thể trả về Collections.emptyList()
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public String getFullName() {
//        return user.getFirstName() + " " + user.getLastName();
//    }

}