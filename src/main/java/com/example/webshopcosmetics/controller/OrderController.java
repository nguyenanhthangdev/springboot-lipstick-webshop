package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.OrderException;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.service.order.OrderService;
import com.example.webshopcosmetics.service.orderDetails.OrderDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class OrderController {
    @Autowired private OrderService orderService;
    @Autowired private OrderDetailsService orderDetailsService;

    @GetMapping("/admin/order")
    public String allOrder(Model model, @RequestParam(name = "order-code", required = false) String orderCode, @RequestParam(name="size", defaultValue = "10") String sizeString,
                           @RequestParam(name="pageNo", defaultValue = "1") String pageNoString, @RequestParam(value = "status", defaultValue = "NO" ) String statusString) {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoString);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        int size = 10;
        try {
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException e) {
            size = 10;
        }
        OrderStatus status = null;
        try {
            if (statusString != null && !statusString.isEmpty()) {
                status = OrderStatus.valueOf(statusString.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            status = null;
        }
        if (status == null) {
            model.addAttribute("status", "");
        } else {
            model.addAttribute("status", statusString);
        }
        Page<Order> orders = orderService.getAllOrder(pageNo, orderCode, size, statusString);
        model.addAttribute("orderCode", orderCode);
        model.addAttribute("totalPage", orders.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("size", size);
        model.addAttribute("active_order", "ACTIVE");
        model.addAttribute("orders", orders);
        return "admin/order/all-order";
    }

    @GetMapping("/admin/order/order-details")
    public String detailsOrder(Model model, @RequestParam("order_code") String orderCode, RedirectAttributes redirectAttributes) {
        model.addAttribute("active_order", "ACTIVE");
        try {
            Order order = orderService.getOrderByOrderCode(orderCode);
            if (order != null) {
                model.addAttribute("order", orderDetailsService.getOrderDetailByOrderId(order));
                return "admin/order/details-order";
            } else {
                String errorMessage = "Đơn hàng không tồn tại";
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/admin/order";
            }
        } catch (OrderException e) {
            String errorMessage = "Đơn hàng không tồn tại";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/order";
        }
    }

    @PostMapping("/admin/save-reason-for-cancellation")
    @ResponseBody
    public ResponseEntity<?> cancelOrderAdmin(@RequestParam(value = "selectedReason") CancellationReason cancellationReason,
                                         @RequestParam(value = "orderID") Long orderID, HttpSession session) {
        try {
            orderService.cancelOrder(cancellationReason, orderID, session);
            return ResponseEntity.ok("Hủy đơn hàng thành công");
        } catch (Exception e) {
            String errorMessage = "Lỗi khi hủy đơn hàng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/admin/delivery-confirmation")
    @ResponseBody
    public ResponseEntity<?> deliveryConfirmation(@RequestParam(value = "orderID") Long orderID, HttpSession session) {
        try {
            orderService.deliveryConfirmation(orderID);
            return ResponseEntity.ok("Xác nhận giao hàng thành công");
        } catch (Exception e) {
            String errorMessage = "Lỗi khi xác nhận giao hàng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/admin/delivered-successfully")
    @ResponseBody
    public ResponseEntity<?> deliveredSuccessfully(@RequestParam(value = "orderID") Long orderID, HttpSession session) {
        try {
            orderService.deliveredSuccessfully(orderID);
            return ResponseEntity.ok("Giao hàng thành công");
        } catch (Exception e) {
            String errorMessage = "Lỗi khi giao hàng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    //////////////////////////////////////////// TRANG CHỦ //////////////////////////////////////////////////


    @GetMapping("/cosmetic/my-order")
    public String myOrder(HttpSession session, Model model) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) {
            List<Order> orders = orderService.getOrderByCustomerId(session);
            List<Order> listOrderAndOrderDetails = orderDetailsService.getAllOrderDetailByOrderId(orders);
            model.addAttribute("listOrderAndOrderDetails", listOrderAndOrderDetails);
            model.addAttribute("checkAllOrder", false);
            model.addAttribute("checkProcessingOrder", false);
            model.addAttribute("checkShippedOrder", false);
            model.addAttribute("checkDeliveredOrder", false);
            model.addAttribute("order_active", "ACTIVE");
            return "client/order/order";
        } else {
            return "redirect:/cosmetic";
        }
    }

    @PostMapping("/cosmetic/save-reason-for-cancellation")
    @ResponseBody
    public ResponseEntity<?> cancelOrder(@RequestParam(value = "selectedReason") CancellationReason cancellationReason,
                                         @RequestParam(value = "orderID") Long orderID, HttpSession session) {
        try {
            orderService.cancelOrder(cancellationReason, orderID, session);
            return ResponseEntity.ok("Hủy đơn hàng thành công");
        } catch (Exception e) {
            String errorMessage = "Lỗi khi hủy đơn hàng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
