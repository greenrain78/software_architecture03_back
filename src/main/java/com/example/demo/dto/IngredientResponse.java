package com.example.demo.dto;

import com.example.demo.domain.RecipeIngredient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientResponse {
    private static final String AMOUNT_PATTERN = "^\\d+(\\.\\d+)?[a-zA-Z]+$";

    @Schema(description = "재료 이름", example = "김치")
    private String name;

    @Schema(description = "재료 양", example = "200g")
    @Pattern(regexp = AMOUNT_PATTERN, message = "수량 정보는 숫자(정수 또는 소수)와 단위로 구성되어야 합니다.")
    private String value;

    public static IngredientResponse from(RecipeIngredient recipeIngredient) {
        return IngredientResponse.builder()
                .name(recipeIngredient.getIngredient().getName())
                .value(recipeIngredient.getAmount() + recipeIngredient.getUnit())
                .build();
    }

}