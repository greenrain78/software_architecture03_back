package com.example.demo.dto;

import com.example.demo.domain.Recipe;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class RecipeUpdateRequest {
    private String name;
    private String description;
    private List<IngredientRequest> ingredients;
    private String writer;

    public void update(Recipe recipe) {
        Optional.ofNullable(name).ifPresent(recipe::setName);
        Optional.ofNullable(description).ifPresent(recipe::setDescription);
        Optional.ofNullable(ingredients).ifPresent(ingredientRequests -> {
            String ingredientString = ingredientRequests.stream()
                    .map(ingredient -> ingredient.getName() + Recipe.AMOUNT_DELIMITER + ingredient.getAmount())
                    .collect(Collectors.joining(", "));
            recipe.setIngredients(ingredientString);
        });
    }
}
