package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientRequest {
    @Schema(description = "재료 이름", example = "김치")
    private String name;

    @Schema(description = "재료 양", example = "200")
    private double amount;

}
