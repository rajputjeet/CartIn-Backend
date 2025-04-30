package com.example.todo.dto.home.banners;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BannersDto {
    private Long id;

    @NotBlank(message = "Banner URL is required")
    private String bannerUrl;
}
