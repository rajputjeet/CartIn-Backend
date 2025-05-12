package com.example.todo.repositories.categoriesRepository;

import com.example.todo.entities.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT c FROM Categories c LEFT JOIN FETCH c.productsList")
    List<Categories> findAllWithProducts();

    @Query("SELECT c FROM Categories c WHERE c.parentCategory.id = :parentId")
    List<Categories> findSubcategoriesByParentId(@Param("parentId") Long parentId);
}
