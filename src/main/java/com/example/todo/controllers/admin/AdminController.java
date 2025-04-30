package com.example.todo.controllers.admin;


import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.product.ProductDto;
import com.example.todo.entities.banners.Banners;
import com.example.todo.entities.categories.Categories;
import com.example.todo.entities.product.Product;
import com.example.todo.entities.response.ApiResponse;
import com.example.todo.services.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/banner")
    public ResponseEntity<ApiResponse<Banners>> createBanner(@Valid @RequestBody BannersDto dto) {
        return ResponseEntity.ok(
                new ApiResponse<>("success",
                        HttpStatus.OK.value(),
                        "Banner created successfully",
                        adminService.createBanner(dto
                        ))
        );
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<Categories>> createCategory(@Valid @RequestBody String name) {

        return ResponseEntity.ok(
                new ApiResponse<>("success",
                        HttpStatus.OK.value(),
                        "Category created successfully",
                        adminService.createCategory(name
                        ))
        );

    }

    @PostMapping("/product")
     public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductDto productDto){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "success",
                        HttpStatus.OK.value(),
                        "Product created successfully",
                        adminService.createProduct(productDto
                        ))
        );

     }


}
