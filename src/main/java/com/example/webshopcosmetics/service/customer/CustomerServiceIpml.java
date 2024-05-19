package com.example.webshopcosmetics.service.customer;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.CustomerException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.repository.CustomerRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Service
public class CustomerServiceIpml implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer findCustomerByAccount(String account) {
        return customerRepository.findByAccount(account);
    }
    @Override
    public Customer registerCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new CategoryException("Đăng kí tài khoản không thành công. Vui lòng thử lại sau!", e);
        }
    }

    @Override
    public Customer authenticateCustomer(String account, String password) {
        Customer customer = customerRepository.findByAccount(account);
        if (customer != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return customer;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Customer myAccount(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) {
            Customer customer = customerRepository.findByAccount(customerDTO.account());
            return customer;
        }
        return null;
    }

    @Override
    public boolean checkIfTheOldPasswordIsCorrect(String account, String password) {
        Customer customer = customerRepository.findByAccount(account);

        // Kiểm tra xem người dùng có tồn tại không
        if(customer != null) {
            // Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
            String hashedPasswordFromDatabase = customer.getPassword();

            // So sánh mật khẩu đã nhập với mật khẩu đã mã hóa từ cơ sở dữ liệu
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password, hashedPasswordFromDatabase);
        }
        return false;
    }

    @Override
    public Page<Customer> getAllCustomer(int pageNo, String keyword, int size, int status) {
        if (keyword==null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }
        if (customerRepository.count() <= size) {
            pageNo = 1;
        }

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, size, sort);
        if (status == 2) {
            // Trường hợp status=null, không lọc theo status
            return customerRepository.findByAccountContaining(keyword, pageable);
        } else {
            boolean boolStatus = false;
            if (status == 1) {
                boolStatus = true;
            }
            // Trường hợp status=true hoặc status=false
            return customerRepository.findByStatusAndAccountContaining(boolStatus, keyword, pageable);
        }
    }
    @Override
    public Customer getOneCustomer(Long id) {
        try {
            return customerRepository.getOne(id);
        } catch (CustomerException e) {
            throw new CustomerException("Khách hàng không tồn tại", e);
        }
    }
    @Override
    public Customer updateCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (CustomerException e) {
            throw new CustomerException("Thay đổi thông tin khách hàng không thành công", e);
        }
    }
    @Override
    public void deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomerException("Xóa tài khoản người dùng không thành công", e);
        };
    }
}