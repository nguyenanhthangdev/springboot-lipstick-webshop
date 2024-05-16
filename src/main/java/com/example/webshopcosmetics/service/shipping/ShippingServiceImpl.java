package com.example.webshopcosmetics.service.shipping;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.exception.ShippingException;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.model.Shipping;
import com.example.webshopcosmetics.repository.CustomerRepository;
import com.example.webshopcosmetics.repository.ShippingRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired private ShippingRepository shippingRepository;
    @Autowired private CustomerRepository customerRepository;
    @Override
    public boolean hasShippingAddress(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        Customer customer = customerRepository.getOne(customerDTO.id());
        return shippingRepository.existsByCustomer(customer);
    }

    @Override
    public Shipping saveShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    @Override
    public Shipping findShippingByCustomerIdAndStatusTrue(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        return shippingRepository.findShippingsByCustomerIdAndStatusTrue(customerDTO.id());
    }

    @Override
    public List<Shipping> findAllShippingByCustomerId(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        return shippingRepository.findAllShippingsByCustomerId(customerDTO.id());
    }

    @Override
    public List<Shipping> getAllAddressOfCustomer(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        return shippingRepository.findAllShippingOfCustomer(customerDTO.id());
    }

    @Override
    public void deactivateAllByCustomerId(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        shippingRepository.deactivateAllByCustomerId(customerDTO.id());
    }

    @Override
    public Shipping getOneShippingById(Long id) {
        Optional<Shipping> optionalShipping = shippingRepository.findById(id);
        Shipping shipping = optionalShipping.orElse(null);
        return shipping;
    }

    @Override
    public void deleteShipingById(Long id) {
        try {
            shippingRepository.deleteById(id);
        } catch (Exception e) {
            throw new ShippingException("Xóa địa chỉ không thành công", e);
        }
    }

    @Override
    public Shipping checkCustomerHasShipping(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        return shippingRepository.findFirstByCustomerIdAndStatusTrue(customerDTO.id());
    }

    @Override
    public void updateStatusTrueByShippingId(Long shippingId) {
        shippingRepository.updateStatusTrueByShippingId(shippingId);
    }
}
