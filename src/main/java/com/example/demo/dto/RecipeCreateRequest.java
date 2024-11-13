package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RecipeCreateRequest {
    @Schema(description = "레시피 이름", example = "김치찌개")
    @NotEmpty(message = "레시피 이름은 필수 항목입니다.")
    private String name;

    @Schema(description = "레시피 설명", example = "다 넣고 끓인다.")
    @NotEmpty(message = "레시피 설명은 필수 항목입니다.")
    private String description;

    @Schema(description = "레시피 재료", example = "[{\"name\":\"김치\",\"value\":\"200g\"},{\"name\":\"두부\",\"value\":\"100g\"}]")
    @NotEmpty(message = "레시피 재료는 필수 항목입니다.")
    private String ingredients;
}
