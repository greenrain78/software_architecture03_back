package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRecommendRequest {
    @Schema(description = "레시피 이름", example = "김치찌개")
    private String name;
    @Schema(description = "레시피 설명", example = "다 넣고 끓인다.")
    private String description;
    @Schema(description = "레시피 재료", example = "[{\"name\":\"김치\",\"amount\":\"200g\"}]")
    private List<Ingredient> ingredients;
}

