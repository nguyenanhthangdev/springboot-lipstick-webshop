package com.example.webshopcosmetics.model;

import java.math.BigDecimal;

public class CartItem {
    private Long productId;
    private Long productOptionId;
    private String productOptionImage;
    private String productName;
    private String productOptionName;
    private int quantity;
    private BigDecimal productOptionPrice;
    private BigDecimal discountPrice;
    private DiscountType discountType;
    private int productOptionAmount;
    // Constructor
    public CartItem(Long productId, Long productOptionId, String productOptionImage, String productName, String productOptionName, int quantity, BigDecimal productOptionPrice, BigDecimal discountPrice, DiscountType discountType, int productOptionAmount) {
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.productOptionImage = productOptionImage;
        this.productName = productName;
        this.productOptionName = productOptionName;
        this.quantity = quantity;
        this.productOptionPrice = productOptionPrice;
        this.discountPrice = discountPrice;
        this.discountType = discountType;
        this.productOptionAmount = productOptionAmount;
    }

    // Getter methods
    public Long getProductId() {
        return productId;
    }
    public Long getProductOptionId() {
        return productOptionId;
    }
    public String getProductOptionImage() {
        return productOptionImage;
    }
    public String getProductOptionName() {
        return productOptionName;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public BigDecimal getProductOptionPrice() {
        return productOptionPrice;
    }
    public BigDecimal getDiscountPrice() { return discountPrice; }
    public DiscountType getDiscountType() {return discountType;}
    public int getProductOptionAmount() {return productOptionAmount;}
    // Setter methods
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }
    public void setProductOptionImage(String productOptionImage) {
        this.productOptionImage = productOptionImage;
    }
    public void setProductOptionName(String productOptionName) {
        this.productOptionName = productOptionName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setProductOptionPrice(BigDecimal productOptionPrice) {
        this.productOptionPrice = productOptionPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
    public void setProductOptionAmount(int productOptionAmount) {this.productOptionAmount = productOptionAmount;}
}
