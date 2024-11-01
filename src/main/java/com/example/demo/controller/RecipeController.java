package com.example.demo.controller;

import com.example.demo.domain.Recipe;
import com.example.demo.dto.IngredientResponse;
import com.example.demo.dto.RecipeCreateRequest;
import com.example.demo.dto.RecipeResponse;
import com.example.demo.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/")
    @Operation(summary = "모든 레시피 조회", description = "모든 레시피를 조회합니다.")
    public ResponseEntity<List<RecipeResponse>> getRecipe() {
        log.info("모든 레시피 조회 요청");
        return ResponseEntity.ok(recipeService.getRecipe());
    }

    @PostMapping("/")
    @Operation(summary = "레시피 생성", description = "레시피를 생성합니다.")
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeCreateRequest recipeCreateRequest) {
        log.info("레시피 생성 요청");
        Recipe recipe = recipeService.createRecipe(recipeCreateRequest);
        List<IngredientResponse> ingredients = recipe.getRecipeIngredients().stream()
                .map(recipeIngredient -> IngredientResponse.builder()
                        .name(recipeIngredient.getIngredient().getName())
                        .value(recipeIngredient.getValue())
                        .build())
                .toList();
        return ResponseEntity.ok(RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(ingredients)
                .build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "레시피 삭제", description = "레시피를 삭제합니다.")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        log.info("레시피 삭제 요청");
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
//    // 레시피 추천
//    @PostMapping("/recommend")
//    @Operation(summary = "레시피 추천", description = "레시피를 추천합니다.")
//    public ResponseEntity<RecipeResponse> recommendRecipe(@RequestBody IngredientListRequest ingredientListRequest) {
//        log.info("레시피 추천 요청");
//        return ResponseEntity.ok(recipeService.recommendRecipe(ingredientListRequest));
//    }
//    // 레시피 검색
//    @GetMapping("/search")
//    @Operation(summary = "레시피 검색", description = "레시피를 검색합니다.")
//    public ResponseEntity<List<RecipeResponse>> searchRecipe(@RequestParam String keyword) {
//        log.info("레시피 검색 요청");
//        return ResponseEntity.ok(recipeService.searchRecipe(keyword));
//    }
}
