package com.example.todo.entities.categories;


import com.example.todo.entities.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
   private List<Product> productsList;
}
