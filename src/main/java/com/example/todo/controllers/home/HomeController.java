package com.example.todo.controllers.home;

import com.example.todo.dto.home.HomeResponseDto;
import com.example.todo.dto.home.categoryDto.subCategory.SubCategoryResponseDto;
import com.example.todo.entities.response.ApiResponse;
import com.example.todo.services.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResponseEntity<ApiResponse<HomeResponseDto>> getHome(){

        HomeResponseDto homeResponseDto = homeService.getHome();
         ApiResponse<HomeResponseDto> response = new ApiResponse<>(
                 "success",
                 HttpStatus.OK.value(),
                 "home data fetched successfully!",
                 homeResponseDto
         );
       return ResponseEntity.ok(response);
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<List<SubCategoryResponseDto>>> getSubCategoriesByParent(@PathVariable Long parentId){

        List<SubCategoryResponseDto> subcategories = homeService.getSubCategoriesByParent(parentId);

        ApiResponse<List<SubCategoryResponseDto>> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Categories fetched successfully",
                subcategories
        );

        return ResponseEntity.ok(response);

    }

}
