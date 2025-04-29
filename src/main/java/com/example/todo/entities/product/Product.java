package com.example.todo.entities.product;


import com.example.todo.entities.categories.Categories;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

   private String productName;
   private String productType;
   private Double price;
   private Double discountedPrice;
   private Double discountPercent;
   private Double rating;
   private Double ratedBy;
   private boolean isLiked;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
}
