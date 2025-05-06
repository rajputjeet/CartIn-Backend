package com.example.todo.services.admin;


import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import com.example.todo.dto.home.categoryDto.subCategory.SubCategoryDto;
import com.example.todo.dto.home.product.ProductDto;
import com.example.todo.entities.banners.Banners;
import com.example.todo.entities.categories.Categories;
import com.example.todo.entities.product.Product;
import com.example.todo.repositories.banners.BannersRepository;
import com.example.todo.repositories.categoriesRepository.CategoryRepository;
import com.example.todo.repositories.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AdminService {

    @Autowired
    private BannersRepository bannersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Banners createBanner(BannersDto dto) {
        Banners banner = new Banners();
        banner.setBannerUrl(dto.getBannerUrl());
        return bannersRepository.save(banner);
    }

    public Categories createCategory(CategoryDto dto) {
        Categories category = new Categories();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public Categories createSubCategory(SubCategoryDto dto) {
        Categories parent = categoryRepository.findById(dto.getParentCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Parent category not found"));

        Categories subCategory = new Categories();
        subCategory.setName(dto.getName());
        subCategory.setParentCategory(parent);

        return categoryRepository.save(subCategory);
    }


    public Product createProduct(ProductDto dto) {
        Categories category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(()->
                new EntityNotFoundException("Category not found"));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setProductType(dto.getProductType());
        product.setPrice(dto.getPrice());
        product.setDiscountedPrice(dto.getDiscountedPrice());
        product.setDiscountPercent(dto.getDiscountPercent());
        product.setRating(dto.getRating());
        product.setRatedBy(dto.getRatedBy());
        product.setLiked(dto.isLiked());
        product.setCategory(category);

        return productRepository.save(product);
    }

}
