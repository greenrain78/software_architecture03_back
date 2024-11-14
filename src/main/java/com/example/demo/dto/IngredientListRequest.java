package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class IngredientListRequest {
    @ArraySchema(schema = @Schema(implementation = IngredientRequest.class))
    private List<IngredientRequest> ingredients;
}
