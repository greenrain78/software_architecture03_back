package com.example.demo.service;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeIngredient;
import com.example.demo.dto.*;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    public List<RecipeResponse> getRecipe() {
        List<Recipe> recipeList = recipeRepository.findAllByOrderByIdDesc();
        return recipeList.stream().map(RecipeResponse::from).toList();
    }

    @Transactional
    public Recipe createRecipe(RecipeCreateRequest recipeCreateRequest) {
        //
        Recipe recipe = Recipe.builder()
                .name(recipeCreateRequest.getName())
                .description(recipeCreateRequest.getDescription())
                .build();
        //
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();
        recipeCreateRequest.getIngredients().forEach(ingredientRequest -> {
            Ingredient ingredient = ingredientRepository.findByName(ingredientRequest.getName())
                    .orElseGet(() -> Ingredient.builder()
                            .name(ingredientRequest.getName())
                            .build());
            RecipeIngredient recipeIngredient = RecipeIngredient.builder()
                    .recipe(recipe)
                    .ingredient(ingredient)
                    .amount(ingredientRequest.getAmount())
                    .unit(ingredientRequest.getUnit())
                    .build();
            recipeIngredients.add(recipeIngredient);
        });
        recipe.setRecipeIngredients(recipeIngredients);
        //
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<RecipeResponse> searchRecipe(String recipeName) {
        List<Recipe> recipeList = recipeRepository.findByName(recipeName);
        return recipeList.stream().map(RecipeResponse::from).toList();
    }

    public List<RecipeResponse> recommendRecipe(IngredientListRequest ingredientListRequest) {
//        List<Recipe> recipeList = recipeRepository.findRecipesByIngredients(ingredientListRequest.getIngredients().stream()
//                .collect(Collectors.toMap(IngredientRequest::getName, IngredientRequest::getAmount)));
//        return recipeList.stream().map(RecipeResponse::from).toList();
        List<Recipe> recipeList = recipeRepository.findRecipesByIngredients(ingredientListRequest.getIngredients().stream()
                .map(IngredientRequest::getName)
                .toList(),
                ingredientListRequest.getIngredients().stream()
                        .collect(Collectors.toMap(IngredientRequest::getName, IngredientRequest::getAmount)),
                ingredientListRequest.getIngredients().size());
        log.info("recipeList: {}", recipeList);
        RecipeResponse recipeResponse = RecipeResponse.builder()
                .id(1L)
                .name("recipe")
                .description("description")
                .ingredients(List.of(IngredientResponse.builder()
                        .name("ingredient")
                        .value(100.0 + "g")
                        .build()))
                .build();
        return List.of(recipeResponse);
    }
}
