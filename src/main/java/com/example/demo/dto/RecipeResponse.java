package com.example.demo.dto;

import com.example.demo.domain.Recipe;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponse {
    private Long id;
    private String name;
    private String description;
    private String ingredients;

    public static RecipeResponse from(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(recipe.getIngredients())
                .build();
    }
}
