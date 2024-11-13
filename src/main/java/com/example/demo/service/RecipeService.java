package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.dto.*;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
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
        return recipeList.stream().map(RecipeResponse::from).toList();
    }

    @Transactional
    public Recipe createRecipe(RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = Recipe.builder()
                .name(recipeCreateRequest.getName())
                .description(recipeCreateRequest.getDescription())
                .ingredients(recipeCreateRequest.getIngredients())
                .build();
        return recipeRepository.save(recipe);
    }
}
