package com.example.webshopcosmetics.service.checkout;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.repository.*;
import com.example.webshopcosmetics.service.shipping.ShippingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    @Autowired private ProductOptionsRepository productOptionsRepository;
    @Autowired private ShippingService shippingService;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;

    @Override
    public void checkTheNumberOfProductsLeftInTheShop(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        System.out.println("0:");
        if (cart != null || !cart.isEmpty()) {
            System.out.println("1:");
            for (CartItem p : cart) {
                System.out.println("1:" + p.getQuantity());
                Optional<ProductOptions> optionalProductOption = productOptionsRepository.findById(p.getProductOptionId());
                ProductOptions productOption = optionalProductOption.orElse(null);
                if (productOption != null) {
                    if ((productOption.getAmount() > 0) && (productOption.getAmount() <= p.getQuantity())) {
                        productOptionsRepository.updateAmountToZero(productOption.getId());
                        p.setQuantity(productOption.getAmount());
                        continue;
                    } else if (productOption.getAmount() <= 0) {
                        p.setQuantity(0);
                        continue;
                    } else if ((productOption.getAmount() > 0) && (productOption.getAmount() > p.getQuantity())) {
                        int amountNew = productOption.getAmount() - p.getQuantity();
                        productOptionsRepository.updateAmount(productOption.getId(), amountNew);
                        p.setQuantity(p.getQuantity());
                        continue;
                    }
                }
            }
        }
    }

    @Override
    public void productsPayment(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null || !cart.isEmpty()) {
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            if (customerDTO != null) {
                Shipping shipping = shippingService.findShippingByCustomerIdAndStatusTrue(session);
                Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.id());
                Customer customer = optionalCustomer.orElse(null);
                if ((shipping != null) && (customer != null)) {
                    Order order = new Order();
                    order.setCustomer(customer);
                    int total = 0;
                    for (CartItem p : cart) {
                        if (p.getDiscountType() == DiscountType.PERCENTAGE) {
                            total += (p.getProductOptionPrice().intValue() - ((p.getProductOptionPrice().intValue() * p.getDiscountPrice().intValue()) / 100)) * p.getQuantity();
                        } else if (p.getDiscountType() == DiscountType.AMOUNT) {
                            total += (p.getProductOptionPrice().intValue() - p.getDiscountPrice().intValue()) * p.getQuantity();
                        } else {
                            total += p.getProductOptionPrice().intValue() * p.getQuantity();
                        }
                    }
                    BigDecimal bigDecimalValue = new BigDecimal(total);
                    order.setTotalAmount(bigDecimalValue);
                    order.setStatus(OrderStatus.PROCESSING);
                    order.setPaymentMethod(PaymentMethod.THANH_TOAN_KHI_NHAN_HANG);
                    order.setShipping(shipping);
                    LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                    order.setOrderDate(currentDateTime);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
                    String formattedDateTime = currentDateTime.format(formatter);
                    String resultOrderCode = formattedDateTime + this.randomStringGenerator();
                    order.setOrderCode(resultOrderCode);
                    Order orderSaved = orderRepository.save(order);
                    if (orderSaved != null) {
                        for (CartItem p : cart) {
                            Optional<ProductOptions> optionalProductOptions = productOptionsRepository.findById(p.getProductOptionId());
                            ProductOptions productOption = optionalProductOptions.orElse(null);
                            if (productOption != null) {
                                OrderDetail orderDetail = new OrderDetail();
                                orderDetail.setOrder(orderSaved);
                                orderDetail.setProductOption(productOption);
                                orderDetail.setQuantity(p.getQuantity());
                                int totalPrice = 0;
                                int unitPrice = 0;
                                if (p.getDiscountType() == DiscountType.PERCENTAGE) {
                                    totalPrice += (p.getProductOptionPrice().intValue() - ((p.getProductOptionPrice().intValue() * p.getDiscountPrice().intValue()) / 100)) * p.getQuantity();
                                    unitPrice += p.getProductOptionPrice().intValue() - ((p.getProductOptionPrice().intValue() * p.getDiscountPrice().intValue()) / 100);
                                } else if (p.getDiscountType() == DiscountType.AMOUNT) {
                                    totalPrice += (p.getProductOptionPrice().intValue() - p.getDiscountPrice().intValue()) * p.getQuantity();
                                    unitPrice += p.getProductOptionPrice().intValue() - p.getDiscountPrice().intValue();
                                } else {
                                    totalPrice += p.getProductOptionPrice().intValue() * p.getQuantity();
                                    unitPrice += p.getProductOptionPrice().intValue();
                                }
                                BigDecimal totalPriceBigDecimal = new BigDecimal(totalPrice);
                                BigDecimal unitPriceBigDecimal = new BigDecimal(unitPrice);
                                orderDetail.setUnitPrice(unitPriceBigDecimal);
                                orderDetail.setTotalPrice(totalPriceBigDecimal);
                                OrderDetail orderDetailSaved = orderDetailRepository.save(orderDetail);
                            }
                        }
                    }
                }
            }
        }
    }
    public String randomStringGenerator () {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int LENGTH = 10;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
