package com.example.demo.dto;

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
}
