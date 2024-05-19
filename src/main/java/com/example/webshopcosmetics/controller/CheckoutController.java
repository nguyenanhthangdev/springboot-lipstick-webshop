package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.CartItem;
import com.example.webshopcosmetics.model.Shipping;
import com.example.webshopcosmetics.service.cart.CartService;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.checkout.CheckoutService;
import com.example.webshopcosmetics.service.city.CityService;
import com.example.webshopcosmetics.service.customer.CustomerService;
import com.example.webshopcosmetics.service.shipping.ShippingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class CheckoutController {
    @Autowired private CityService cityService;
    @Autowired private CustomerService customerService;
    @Autowired private ShippingService shippingService;
    @Autowired private CategoryService categoryService;
    @Autowired private CheckoutService checkoutService;
    @Autowired private CartService cartService;

    @GetMapping("/cosmetic/checkout")
    public String checkout(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) {
                cart = new ArrayList<>();
            }
            model.addAttribute("cart", cart);
            model.addAttribute("cities", cityService.getAllCity());
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("shipping", shippingService.findShippingByCustomerIdAndStatusTrue(session));
            return "/client/checkout/checkout";
//        }
    }

    @GetMapping("/cosmetic/check-logged-checkout")
    @ResponseBody
    public boolean checkLoggedCheckout1(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) { return true; }
        else { return false; }
    }

    @PostMapping("/cosmetic/check-the-number-of-products-left-in-the-shop")
    @ResponseBody
    public ResponseEntity<?> checkTheNumberOfProductsLeftInTheShop(HttpSession session) {
        try {
            checkoutService.checkTheNumberOfProductsLeftInTheShop(session);
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            boolean checkIfThereIsAChangeInProductQuantity = false;
            if (cart != null || !cart.isEmpty()) {
                Iterator<CartItem> iterator = cart.iterator();
                while (iterator.hasNext()) {
                    CartItem p = iterator.next();
                    if (p.getQuantity() <= 0) {
                        iterator.remove();
                    } else {
                        checkIfThereIsAChangeInProductQuantity = true;
                    }
                }
            }
            if (checkIfThereIsAChangeInProductQuantity) {
                return ResponseEntity.ok(true);
            } else {
                cartService.deleteAllItemToCartInDataBase(session);
                session.removeAttribute("cart");
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            String errorMessage = "Lỗi khi kiểm tra số lượng sản phẩm hiện có trong shop";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/products-payment")
    @ResponseBody
    public ResponseEntity<?> productsPayment(HttpSession session) {
        try {
            checkoutService.productsPayment(session);
            cartService.deleteAllItemToCartInDataBase(session);
            session.removeAttribute("cart");
            return ResponseEntity.ok("Thanh toán thành công");
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thanh toán sản phẩm";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
