package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class RecipeCreateRequest {
    @Schema(description = "레시피 이름", example = "김치찌개")
    @NotEmpty(message = "레시피 이름은 필수 항목입니다.")
    private String name;

    @Schema(description = "레시피 설명", example = "다 넣고 끓인다.")
    @NotEmpty(message = "레시피 설명은 필수 항목입니다.")
    private String description;

    @ArraySchema(schema = @Schema(implementation = IngredientRequest.class))
    @NotEmpty(message = "레시피 재료는 필수 항목입니다.")
    private List<IngredientRequest> ingredients;
    public String getIngredients() {
        return ingredients.stream()
                .map(ingredient -> ingredient.getName() + ":" + ingredient.getValue())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
