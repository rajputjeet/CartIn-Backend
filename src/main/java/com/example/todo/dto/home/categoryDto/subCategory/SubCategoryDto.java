package com.example.todo.dto.home.categoryDto.subCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {
    @NotBlank(message = "Name is required")
    private String name;

    private Long parentCategoryId;
}
