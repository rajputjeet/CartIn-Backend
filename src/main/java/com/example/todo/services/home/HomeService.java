package com.example.todo.services.home;

import com.example.todo.dto.home.HomeResponseDto;
import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import com.example.todo.dto.home.product.ProductDto;
import com.example.todo.entities.categories.Categories;
import com.example.todo.entities.product.Product;
import com.example.todo.repositories.banners.BannersRepository;
import com.example.todo.repositories.categoriesRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .filter(c -> c.getParentCategory() == null) // get only top-level categories
                .map(this::mapCategoryToDto)
                .toList();

        return new HomeResponseDto(banners, categories);
    }

    private CategoryDto mapCategoryToDto(Categories category) {
        List<ProductDto> products = Optional.ofNullable(category.getProductsList())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapProductToDto)
                .collect(Collectors.toList());

        List<CategoryDto> subCategories = Optional.ofNullable(category.getSubCategories())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapCategoryToDto) // recursive
                .collect(Collectors.toList());

        return new CategoryDto(category.getId(), category.getName(), products, subCategories);
    }

    private ProductDto mapProductToDto(Product p) {
        return new ProductDto(
                p.getId(),
                p.getProductName(),
                p.getProductType(),
                p.getPrice(),
                p.getDiscountedPrice(),
                p.getDiscountPercent(),
                p.getRating(),
                p.getRatedBy(),
                p.isLiked(),
                p.getCategory() != null ? p.getCategory().getId() : null
        );
    }
}
