package com.example.todo.dto.home;

import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class HomeResponseDto {
    private List<BannersDto> banners;
    private List<CategoryDto> categories;
}
