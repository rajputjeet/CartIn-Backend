package com.example.todo.entities.categories;


import com.example.todo.entities.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Categories parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Categories> subCategories;

   @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
   private List<Product> productsList;
}
