package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.RegisterException;
import com.example.webshopcosmetics.exception.ShippingException;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.service.city.CityService;
import com.example.webshopcosmetics.service.customer.CustomerService;
import com.example.webshopcosmetics.service.district.DistrictService;
import com.example.webshopcosmetics.service.shipping.ShippingService;
import com.example.webshopcosmetics.service.ward.WardService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShippingController {
    @Autowired private ShippingService shippingService;
    @Autowired private CustomerService customerService;
    @Autowired private CityService cityService;
    @Autowired private DistrictService districtService;
    @Autowired private WardService wardService;

    @GetMapping("/cosmetic/check-the-user-has-address-information")
    @ResponseBody
    public boolean checkTheCustomerHasAddressInformation(HttpSession session) {
        boolean check = shippingService.hasShippingAddress(session);
        return check;
    }

    @PostMapping("/cosmetic/save-shipping-of-customer")
    @ResponseBody
    public ResponseEntity<?> saveShipping(@RequestParam("fullName") String fullName, @RequestParam("phone") String phone,
                                 @RequestParam("email") String email, @RequestParam("city") Long city,
                                 @RequestParam("district") Long district, @RequestParam("ward") Long ward,
                                 @RequestParam("addressSpecific") String addressSpecific, @RequestParam("addressType") AddressType addressType, HttpSession session) {
        try {
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            Shipping shipping = new Shipping();
            shipping.setFullName(fullName);
            shipping.setPhone(phone);
            shipping.setEmail(email);
            shipping.setCity(cityService.getCityById(city));
            shipping.setDistrict(districtService.getDistrictById(district));
            shipping.setWard(wardService.getWardById(ward));
            shipping.setAddressSpecific(addressSpecific);
            shipping.setAddressType(addressType);
            shipping.setStatus(true);
            shipping.setCustomer(customerService.getOneCustomer(customerDTO.id()));
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            shipping.setCreatedAt(currentDateTime);
            shipping.setUpdatedAt(currentDateTime);
            Shipping shipping1 = shippingService.saveShipping(shipping);
            return ResponseEntity.ok("Thêm địa chỉ nhận hàng của người dùng thành công");
        } catch (ShippingException e) {
            String errorMessage = "Lỗi khi thêm địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/save-shipping-of-customer2")
    @ResponseBody
    public ResponseEntity<?> saveShipping2(@RequestParam("fullName") String fullName, @RequestParam("phone") String phone, @RequestParam("email") String email,
                                          @RequestParam("city") Long city, @RequestParam("district") Long district, @RequestParam("ward") Long ward, @RequestParam("isDefault") boolean isDefault,
                                          @RequestParam("addressSpecific") String addressSpecific, @RequestParam("addressType") AddressType addressType, HttpSession session) {
        try {
            System.out.println(isDefault);
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            Shipping shipping = new Shipping();
            shipping.setFullName(fullName);
            shipping.setPhone(phone);
            shipping.setEmail(email);
            shipping.setCity(cityService.getCityById(city));
            shipping.setDistrict(districtService.getDistrictById(district));
            shipping.setWard(wardService.getWardById(ward));
            shipping.setAddressSpecific(addressSpecific);
            shipping.setAddressType(addressType);
            shipping.setCustomer(customerService.getOneCustomer(customerDTO.id()));
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            shipping.setCreatedAt(currentDateTime);
            shipping.setUpdatedAt(currentDateTime);
            if (isDefault) {
                shippingService.deactivateAllByCustomerId(session);
                shipping.setStatus(true);
            } else {
                shipping.setStatus(false);
            }
            Shipping savedShipping = shippingService.saveShipping(shipping);
            boolean isSuccess = savedShipping != null;
            return ResponseEntity.ok(isSuccess);
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thêm địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/cosmetic/get-all-address-of-customer")
    @ResponseBody
    public ResponseEntity<?> getAllAddressOfCustomer(HttpSession session) {
        try {
            List<Shipping> listShipping = shippingService.getAllAddressOfCustomer(session);
            return ResponseEntity.ok(listShipping);
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thêm địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/delete-shipping-by-id-of-customer")
    @ResponseBody
    public ResponseEntity<?> deleteAddressOfCustomer(@RequestParam("id") Long id, HttpSession session) {
        System.out.println("id: " + id);
        try {
            Shipping shipping = shippingService.getOneShippingById(id);
            if (shipping != null) {
                System.out.println("shipping: " + shipping);
                if (shipping.isStatus() == true) {
                    System.out.println("status1: " + shipping.isStatus());
                    return ResponseEntity.ok(false);
                } else {
                    System.out.println("status2: " + shipping.isStatus());
                    shippingService.deleteShipingById(id);
                    return ResponseEntity.ok(true);
                }
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thay đổi địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/cosmetic/check-customer-has-shipping")
    @ResponseBody
    public ResponseEntity<?> checkCustomerHasShipping(HttpSession session) {
        try {
            Shipping shipping = shippingService.checkCustomerHasShipping(session);
            if (shipping != null) {
                System.out.println("checkCustomerHasShipping");
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thay đổi địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/change-this-address-to-default-in-DB")
    @ResponseBody
    public ResponseEntity<?> changeThisAddressToDefaultInDB(@RequestParam("idDefaultShipping") Long shippingId, HttpSession session) {
        try {
            shippingService.deactivateAllByCustomerId(session);
            shippingService.updateStatusTrueByShippingId(shippingId);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thay đổi địa chỉ nhận hàng của người dùng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
