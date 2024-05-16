package com.example.webshopcosmetics.config;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLDecoder;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Kiểm tra cookie có tên là "myCookie"
                if ("customerData".equals(cookie.getName())) {
                    String customerDataEncoded = cookie.getValue();
                    String customerDataDecoded = URLDecoder.decode(customerDataEncoded, "UTF-8");

                    // Chuyển đổi chuỗi JSON thành đối tượng JsonNode
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(customerDataDecoded);

                    // Truy cập trường "customerId"
                    Long customerId = jsonNode.get("customerId").asLong();
                    String customerName = jsonNode.get("customerName").asText();
                    String customerAccount = jsonNode.get("customerAccount").asText();

                    HttpSession session = request.getSession();
                    session.setAttribute("s_customer", new CustomerDTO(customerId, customerName, customerAccount));
                    break; // Dừng vòng lặp khi tìm thấy cookie cần
                }
            }
        }
        return true; // Tiếp tục xử lý yêu cầu
    }
}


