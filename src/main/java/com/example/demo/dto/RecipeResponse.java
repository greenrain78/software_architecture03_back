package com.example.demo.dto;

import com.example.demo.domain.Recipe;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeResponse {
    private Long id;
    private String name;
    private String description;
    private List<IngredientResponse> ingredients;

    public static RecipeResponse from(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(recipe.getRecipeIngredients().stream()
                        .map(IngredientResponse::from)
                        .toList())
                .build();
    }
}
