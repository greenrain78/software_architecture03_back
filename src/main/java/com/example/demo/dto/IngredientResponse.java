package com.example.demo.dto;

import com.example.demo.domain.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientResponse {
    @Schema(description = "재료 이름", example = "김치")
    private String name;

    @Schema(description = "재료 양", example = "200")
    private double amount;

    public static IngredientResponse from(String ingredient) {
        String[] ingredientInfo = ingredient.split(Recipe.AMOUNT_DELIMITER);
        return IngredientResponse.builder()
                .name(ingredientInfo[0])
                .amount(Double.parseDouble(ingredientInfo[1]))
                .build();
    }
}
