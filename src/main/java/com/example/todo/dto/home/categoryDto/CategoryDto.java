package com.example.todo.dto.home.categoryDto;

import com.example.todo.dto.home.product.ProductDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotBlank(message = "Category is required")
    private String name;

    private List<ProductDto> products;

    private List<CategoryDto> subCategories; // Recursive nesting


}
