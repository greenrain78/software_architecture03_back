package com.example.demo.controller;

import com.example.demo.domain.Recipe;
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
    public ResponseEntity<RecipeResponse> createRecipe(RecipeCreateRequest recipeCreateRequest) {
        log.info("레시피 생성 요청");
        return ResponseEntity.ok(recipeService.createRecipe(recipeCreateRequest));
    }
    @PutMapping("/{id}")
    @Operation(summary = "레시피 수정", description = "레시피를 수정합니다.")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable Long id, RecipeCreateRequest recipeCreateRequest) {
        log.info("레시피 수정 요청");
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipeCreateRequest));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "레시피 삭제", description = "레시피를 삭제합니다.")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        log.info("레시피 삭제 요청");
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
}
