package com.example.todo.services.home;


import com.example.todo.dto.home.HomeResponseDto;
import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import com.example.todo.dto.home.product.ProductDto;
import com.example.todo.repositories.banners.BannersRepository;
import com.example.todo.repositories.categoriesRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private BannersRepository bannersRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public HomeResponseDto getHome() {

        List<BannersDto> banners = bannersRepository.findAll().stream()
                .map(b -> new BannersDto(b.getId(), b.getBannerUrl())).toList();

        List<CategoryDto> categories = categoryRepository.findAllWithProducts().stream()
                .map(c -> new CategoryDto(c.getId(), c.getName(), c.getProductsList() != null ? c.getProductsList().stream()
                        .map(p -> new ProductDto(p.getId(),
                                p.getProductName(),
                                p.getProductType(),
                                p.getPrice(),
                                p.getDiscountedPrice(),
                                p.getDiscountPercent(),
                                p.getRating(),
                                p.getRatedBy(),
                                p.isLiked()))
                        .toList() : List.of()
                        ))
                .toList();

        return new HomeResponseDto(
                banners,
                categories
        );
    }


}
