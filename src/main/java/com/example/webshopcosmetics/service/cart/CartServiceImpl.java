package com.example.webshopcosmetics.service.cart;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.repository.CartDataItemRepository;
import com.example.webshopcosmetics.repository.CartRepository;
import com.example.webshopcosmetics.repository.CustomerRepository;
import com.example.webshopcosmetics.repository.ProductOptionsRepository;
import com.example.webshopcosmetics.service.product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired private CartRepository cartRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProductOptionsRepository productOptionsRepository;
    @Autowired private CartDataItemRepository cartDataItemRepository;
    @Autowired private ProductService productService;

    public void addItemToCartInDataBase(Long productId, Long productOptionId, int quantity, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        if (customer != null) {
            // Kiểm tra xem người dùng đã có giỏ hàng chưa
            Cart existingCart = cartRepository.findByCartInDataBase(customerId);
            // Tạo giỏ hàng mới nếu người dùng chưa có giỏ hàng
            System.out.println("existingCart:" + existingCart);
            if (existingCart == null) {
                existingCart = new Cart();
                // Set thông tin khách hàng vào giỏ hàng
                LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                existingCart.setCreatedAt(currentDateTime);
                existingCart.setUpdatedAt(currentDateTime);
                existingCart.setCustomer(customerRepository.getOne(customerId));
                Cart savedCart = cartRepository.save(existingCart);
                if (savedCart != null) {
                    CartDataItem cartDataItem = new CartDataItem();
                    cartDataItem.setProduct_id(productId);
                    cartDataItem.setCart(savedCart);
                    cartDataItem.setQuantity(quantity);
                    cartDataItem.setProduct_options_id(productOptionsRepository.getOne(productOptionId));
                    cartDataItemRepository.save(cartDataItem);
                }
            } else {
                System.out.println("ex: "+existingCart.getCart_id());
                CartDataItem cartDataItem = cartDataItemRepository.findCartDataOptionsByCartIdAndProductOptionsId(existingCart.getCart_id(), productOptionId);
                System.out.println("exe: "+existingCart);
                if (cartDataItem != null) {
                    cartDataItem.setQuantity(cartDataItem.getQuantity() + quantity);
                    LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                    existingCart.setUpdatedAt(currentDateTime);
                    cartDataItemRepository.save(cartDataItem);
                } else {
                    CartDataItem cartDataItemNew = new CartDataItem();
                    cartDataItemNew.setProduct_id(productId);
                    cartDataItemNew.setCart(existingCart);
                    cartDataItemNew.setQuantity(quantity);
                    cartDataItemNew.setProduct_options_id(productOptionsRepository.getOne(productOptionId));
                    cartDataItemRepository.save(cartDataItemNew);
                }
            }
        }
    }
    @Override
    public void updateQuantityItemToCartInDataBase(Long customerId, Long productOptionId, int quantity) {
        Cart existingCart = cartRepository.findByCartInDataBase(customerId);
        CartDataItem cartDataItem = cartDataItemRepository.findCartDataOptionsByCartIdAndProductOptionsId(existingCart.getCart_id(), productOptionId);
        if (cartDataItem != null) {
            cartDataItem.setQuantity(quantity);
            cartDataItemRepository.save(cartDataItem);
        }
    }

    @Override
    public void deleteItemToCartInDataBase(Long customerId, Long productOptionId) {
        Cart existingCart = cartRepository.findByCartInDataBase(customerId);
        CartDataItem cartDataItem = cartDataItemRepository.findCartDataOptionsByCartIdAndProductOptionsId(existingCart.getCart_id(), productOptionId);
        if (cartDataItem != null) {
           cartDataItemRepository.deleteById(cartDataItem.getCart_item_id());
        }
    }

    @Override
    public void deleteAllItemToCartInDataBase(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) {
            Cart existingCart = cartRepository.findByCartInDataBase(customerDTO.id());
            if (existingCart != null) {
                cartDataItemRepository.deleteByCartId(existingCart.getCart_id());
            }
        }
    }

    @Override
    public ProductOptions findProductOptionsById(Long productOptionsId) {
        ProductOptions productOptions = productOptionsRepository.getOne(productOptionsId);
        return productOptions;
    }
    @Override
    public void getAllProductOptionsOfCustomerInCartDB(Long customer_id, HttpServletRequest request) {
        Cart cart = cartRepository.findByCartInDataBase(customer_id);
        if (cart != null) {
            List<CartDataItem> listCartDataItem = cartDataItemRepository.findAllCartDataOptionsByCartId(cart.getCart_id());
            this.includeTheCustomerOfDBShoppingCartIntoTheSession(listCartDataItem, request);
        }
    }

    @Override
    public void addAllItemToCartInDataBase(Customer customer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {
            Cart existingCart = new Cart();
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            existingCart.setCreatedAt(currentDateTime);
            existingCart.setUpdatedAt(currentDateTime);
            existingCart.setCustomer(customerRepository.getOne(customer.getCustomer_id()));
            Cart savedCart = cartRepository.save(existingCart);
            for (CartItem item : cart) {
                CartDataItem cartDataItem = new CartDataItem();
                cartDataItem.setProduct_id(item.getProductId());
                cartDataItem.setCart(savedCart);
                cartDataItem.setQuantity(item.getQuantity());
                cartDataItem.setProduct_options_id(productOptionsRepository.getOne(item.getProductOptionId()));
                cartDataItemRepository.save(cartDataItem);
            }
        }
    }

    public void includeTheCustomerOfDBShoppingCartIntoTheSession(List<CartDataItem> listCartDataItem, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {
            cart.clear();
        }
        List<CartItem> cartNew = (List<CartItem>) session.getAttribute("cart");
        for (CartDataItem cartDataItem : listCartDataItem) {
            Product product = productService.getOneProductById(cartDataItem.getProduct_id());
            CartItem newItem = new CartItem(cartDataItem.getProduct_id(), cartDataItem.getProduct_options_id().getId(), cartDataItem.getProduct_options_id().getImage(), product.getName(),
                    cartDataItem.getProduct_options_id().getName(), cartDataItem.getQuantity(), cartDataItem.getProduct_options_id().getPrice(), product.getDiscountValue(), product.getDiscountType(), cartDataItem.getProduct_options_id().getAmount());
            cart.add(newItem);
        }
    }
}
