package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.dto.RecipeCreateRequest;
import com.example.demo.dto.RecipeResponse;
import com.example.demo.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<RecipeResponse> getRecipe() {
        List<Recipe> recipeList = recipeRepository.findAllByOrderByIdDesc();
        return recipeList.stream().map(recipe -> RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .ingredients(recipe.getIngredients())
                .build()).toList();
    }

    public RecipeResponse createRecipe(RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = Recipe.builder()
                .name(recipeCreateRequest.getName())
                .description(recipeCreateRequest.getDescription())
                .ingredients(recipeCreateRequest.getIngredients())
                .build();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeResponse.builder()
                .id(savedRecipe.getId())
                .name(savedRecipe.getName())
                .description(savedRecipe.getDescription())
                .ingredients(savedRecipe.getIngredients())
                .build();
    }

    public RecipeResponse updateRecipe(Long id, RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 레시피가 존재하지 않습니다."));
        recipe.setName(recipeCreateRequest.getName());
        recipe.setDescription(recipeCreateRequest.getDescription());
        recipe.setIngredients(recipeCreateRequest.getIngredients());
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeResponse.builder()
                .id(savedRecipe.getId())
                .name(savedRecipe.getName())
                .description(savedRecipe.getDescription())
                .ingredients(savedRecipe.getIngredients())
                .build();
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
