package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.CartDataItem;
import com.example.webshopcosmetics.model.CartItem;
import com.example.webshopcosmetics.model.DiscountType;
import com.example.webshopcosmetics.repository.CartDataItemRepository;
import com.example.webshopcosmetics.service.cart.CartService;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.customer.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {
    @Autowired private CustomerService customerService;
    @Autowired private CartService cartService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/cosmetic/add-to-cart")
    @ResponseBody
    public List<CartItem> addToCart(@RequestParam("productOptionId") Long productOptionId, @RequestParam("productId") Long productId, @RequestParam("quantity") int quantity,
                                    @RequestParam("productOptionImage") String productOptionImage, @RequestParam("productOptionName") String productOptionName, @RequestParam("productOptionAmount") int productOptionAmount,
                                    @RequestParam("productName") String productName, @RequestParam("productOptionPrice") BigDecimal productOptionPrice,
                                    @RequestParam("priceDiscount") BigDecimal priceDiscount, @RequestParam("typeDiscount") DiscountType typeDiscount, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        if (cartService.findProductOptionsById(productOptionId) == null) {
            return cart;
        } else {
            boolean found = false;
            for (CartItem p : cart) {
                if (p.getProductOptionId().equals(productOptionId)) {
                    p.setQuantity(p.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                CartItem newItem = new CartItem(productId, productOptionId, productOptionImage, productName, productOptionName, quantity, productOptionPrice, priceDiscount, typeDiscount, productOptionAmount);
                cart.add(newItem);
            }
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            if (customerDTO != null) {
                cartService.addItemToCartInDataBase(productId, productOptionId, quantity, customerDTO.id());
            }
            return cart;
        }
    }

    @GetMapping("/cosmetic/cart")
    public String getCart(Model model, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cart", cart);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "client/cart/cart";
    }

    @GetMapping("/cosmetic/cart-mini")
    @ResponseBody
    public List<CartItem> getCartMini(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        return cart;
    }

    @GetMapping("/cosmetic/update-quantity-cart")
    @ResponseBody
    public String updateQuantityCart(@RequestParam("productOptionId") Long productOptionId, @RequestParam("quantity") int quantity, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (cart == null || cart.isEmpty()) {
            return "Cart is empty";
        }
        for (CartItem item : cart) {
            if (item.getProductOptionId().equals(productOptionId)) {
                if (quantity >= 1) {
                    item.setQuantity(quantity);
                    if (customerDTO != null) {
                        cartService.updateQuantityItemToCartInDataBase(customerDTO.id(), productOptionId, quantity);
                    }
                }
                return "Cart updated successfully";
            }
        }

        return "Product not found in cart";
    }

    @GetMapping("/cosmetic/delete-product-to-cart")
    @ResponseBody
    public List<CartItem> deleteProductToCart(@RequestParam("productOptionId") Long productOptionId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (cart == null || cart.isEmpty()) {
            return new ArrayList<>();
        }
        Iterator<CartItem> iterator = cart.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProductOptionId().equals(productOptionId)) {
                iterator.remove();
                if (customerDTO != null) {
                    cartService.deleteItemToCartInDataBase(customerDTO.id(), productOptionId);
                }
                break;
            }
        }
        return cart;
    }

    @GetMapping("/cosmetic/load-number-product-in-cart")
    @ResponseBody
    public List<CartItem> LoadNumberProductInCart(HttpSession session, HttpServletRequest request) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            if (customerDTO != null) {
                cartService.getAllProductOptionsOfCustomerInCartDB(customerDTO.id(), request);
            }
            return new ArrayList<>();
        }
        return cart;
    }
}

