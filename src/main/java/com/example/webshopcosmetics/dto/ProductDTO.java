package com.example.webshopcosmetics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {
    @NotBlank(message = "Vui lòng nhập giá trị giảm giá")
    @PositiveOrZero(message = "Giá trị giảm giá phải là số nguyên dương hoặc 0")
    private String discountValue;

}
