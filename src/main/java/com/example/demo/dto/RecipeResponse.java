package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponse {
    private Long id;
    private String name;
    private String description;
    private String ingredients;
}
