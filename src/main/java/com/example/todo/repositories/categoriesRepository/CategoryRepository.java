package com.example.todo.repositories.categoriesRepository;

import com.example.todo.entities.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT c FROM Categories c LEFT JOIN FETCH c.productsList")
    List<Categories> findAllWithProducts();
}
