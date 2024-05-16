package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.CartDataItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDataItemRepository extends JpaRepository<CartDataItem, Long> {
    @Query("SELECT c FROM CartDataItem c WHERE c.product_options_id.id = :product_options_id AND c.cart.cart_id = :cart_id")
    CartDataItem findCartDataOptionsByCartIdAndProductOptionsId(Long cart_id, Long product_options_id);
    @Query("SELECT c FROM CartDataItem c WHERE c.cart.cart_id = :cart_id")
    List<CartDataItem> findAllCartDataOptionsByCartId(Long cart_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartDataItem cdi WHERE cdi.cart.cart_id = :cartId")
    void deleteByCartId(Long cartId);
}
