package com.example.todo.services.home;


import com.example.todo.dto.home.HomeResponseDto;
import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import com.example.todo.dto.home.product.ProductDto;
import com.example.todo.repositories.banners.BannersRepository;
import com.example.todo.repositories.categoriesRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private BannersRepository bannersRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public HomeResponseDto getHome() {

        List<BannersDto> banners = Optional.ofNullable(bannersRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(b -> new BannersDto(b.getId(), b.getBannerUrl()))
                .toList();

        List<CategoryDto> categories = Optional.ofNullable(categoryRepository.findAllWithProducts())
                .orElse(Collections.emptyList())
                .stream()
                .map(c -> {
                    List<ProductDto> products = Optional.ofNullable(c.getProductsList())
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(p -> new ProductDto(
                                    p.getId(),
                                    p.getProductName(),
                                    p.getProductType(),
                                    p.getPrice(),
                                    p.getDiscountedPrice(),
                                    p.getDiscountPercent(),
                                    p.getRating(),
                                    p.getRatedBy(),
                                    p.isLiked(),
                                    p.getCategory().getId()))
                            .toList();
                    return new CategoryDto(c.getId(), c.getName(), products);
                })
                .toList();

        return new HomeResponseDto(banners, categories);
    }


}
