package com.example.todo.controllers.home;

import com.example.todo.dto.home.HomeResponseDto;
import com.example.todo.entities.response.ApiResponse;
import com.example.todo.services.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
