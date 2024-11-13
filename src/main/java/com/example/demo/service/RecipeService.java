package com.example.demo.service;

import com.example.demo.domain.Recipe;
import com.example.demo.dto.*;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
}
