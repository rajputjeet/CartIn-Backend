package com.example.todo.dto.home.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private String productType;
    private Double price;
    private Double discountedPrice;
    private Double discountPercent;
    private Double rating;
    private Double ratedBy;
    private boolean isLiked;

}
