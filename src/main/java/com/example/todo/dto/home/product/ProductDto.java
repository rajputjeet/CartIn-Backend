package com.example.todo.dto.home.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;
    @NotBlank(message = "Product type is required")
    private String productType;
    @NotNull(message = "Product price is required")
    private Double price;
    private Double discountedPrice;
    private Double discountPercent;
    private Double rating;
    private Double ratedBy;
    private boolean isLiked;
    @NotNull(message = "Category ID is required")
    private Long categoryId;

}
