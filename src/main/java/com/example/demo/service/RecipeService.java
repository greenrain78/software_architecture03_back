package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.dto.*;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<RecipeResponse> getRecipe() {
        List<Recipe> recipeList = recipeRepository.findAllByOrderByIdDesc();
        return recipeList.stream().map(RecipeResponse::from).toList();
    }

    @Transactional
    public RecipeResponse createRecipe(RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = recipeCreateRequest.toEntity();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeResponse.from(savedRecipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public RecipeResponse updateRecipe(Long id, RecipeUpdateRequest recipeUpdateRequest) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("레시피가 존재하지 않습니다."));
        Optional.ofNullable(recipe.getWriter())
                .filter(writer -> writer.equals(recipeUpdateRequest.getWriter()))
                .orElseThrow(() -> new IllegalArgumentException("작성자만 레시피를 수정할 수 있습니다."));
        recipeUpdateRequest.update(recipe);
        recipeRepository.save(recipe);
        return RecipeResponse.from(recipe);
    }

    public List<RecipeResponse> searchRecipe(String recipeName) {
        List<Recipe> recipeList = recipeRepository.findByNameContaining(recipeName);
        return recipeList.stream().map(RecipeResponse::from).toList();
    }

    public List<RecipeResponse> recommendRecipe(IngredientListRequest ingredientListRequest) {
        List<Recipe> recipeList = recipeRepository.findAllByOrderByIdDesc();
        List<IngredientRequest> ingredients = ingredientListRequest.getIngredients();
        return recipeList.stream()
                .filter(recipe -> isRecipePossible(recipe, ingredients))
                .map(RecipeResponse::from)
                .toList();
    }
    private boolean isRecipePossible(Recipe recipe, List<IngredientRequest> userIngredients) {
        List<IngredientResponse> recipeIngredients = Arrays.stream(recipe.getIngredients().split(", "))
                .map(IngredientResponse::from)
                .toList();
        // 사용자가 입력한 재료가 레시피에 필요한 모든 재료를 충족하는지 확인
        for (IngredientResponse recipeIngredient : recipeIngredients) {
            boolean isIngredientAvailable = userIngredients.stream()
                    .anyMatch(userIngredient -> userIngredient.getName().equals(recipeIngredient.getName()) &&
                                userIngredient.getAmount() >= recipeIngredient.getAmount()
                            );

            if (!isIngredientAvailable) {
                return false; // 필요한 재료가 부족할 경우 false 반환
            }
        }
        return true;
    }
}

