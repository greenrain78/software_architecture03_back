package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecipeCreateRequest {
    @Schema(description = "레시피 이름", example = "김치찌개")
    private String name;
    @Schema(description = "레시피 설명", example = "다 넣고 끓인다.")
    private String description;
    @Schema(description = "레시피 재료", example = "김치: 200g, 두부: 100g")
    private String ingredients;
}
