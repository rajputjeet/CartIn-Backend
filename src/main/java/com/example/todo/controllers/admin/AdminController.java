package com.example.todo.controllers.admin;


import com.example.todo.customClases.ValidationUtils;
import com.example.todo.dto.home.banners.BannersDto;
import com.example.todo.dto.home.categoryDto.CategoryDto;
import com.example.todo.dto.home.categoryDto.subCategory.SubCategoryDto;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/banner", consumes = {"application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<ApiResponse<Banners>> createBanner(@Valid @RequestBody BannersDto dto) {
        return ResponseEntity.ok(
                new ApiResponse<>("success",
                        HttpStatus.OK.value(),
                        "Banner created successfully",
                        adminService.createBanner(dto
                        ))
        );
    }

    @PostMapping(value = "/category", consumes = {"application/json", "application/x-www-form-urlencoded" })
    public ResponseEntity<ApiResponse<?>> createCategory(@Valid @RequestBody CategoryDto dto,
                                                                  BindingResult result) {
        System.out.println("Received CategoryDto: " + dto.getName());
        System.out.println("Received CategoryDto: " + String.valueOf(dto));
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "fail",
                    400,
                    "Validation failed",
                    ValidationUtils.extractErrors(result)
            ));
        }
        return ResponseEntity.ok(
                new ApiResponse<>("success",
                        HttpStatus.OK.value(),
                        "Category created successfully",
                        adminService.createCategory(dto
                        ))
        );

    }
    @PostMapping(value = "/subcategory", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public ResponseEntity<ApiResponse<Categories>> createSubCategory(@Valid @RequestBody SubCategoryDto dto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "success",
                        HttpStatus.OK.value(),
                        "Subcategory created successfully",
                        adminService.createSubCategory(dto)
                )
        );
    }


    @PostMapping(value = "/product", consumes = {"application/json", "application/x-www-form-urlencoded"})
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
