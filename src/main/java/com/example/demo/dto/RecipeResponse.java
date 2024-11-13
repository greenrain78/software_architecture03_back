package com.example.demo.dto;

import com.example.demo.domain.Recipe;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class RecipeResponse {
    private Long id;
    private String name;
    private String description;
        private List<IngredientResponse> ingredients;
    private String writer;

    public static RecipeResponse from(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(Arrays.stream(recipe.getIngredients().split(", "))
                    .map(IngredientResponse::from)
                    .toList())
                .writer(recipe.getWriter())
                .build();
    }
}
