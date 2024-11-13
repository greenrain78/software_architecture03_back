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
    public RecipeResponse createRecipe(RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = recipeCreateRequest.toEntity();
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeResponse.from(savedRecipe);
    }
}
