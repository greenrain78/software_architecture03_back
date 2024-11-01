package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class IngredientListRequest {
    @Schema(description = "보유 식재료", example = "[{\"name\":\"김치\",\"value\":\"200g\"},{\"name\":\"두부\",\"value\":\"100g\"}]")
    private List<IngredientRequest> ingredients;
}

