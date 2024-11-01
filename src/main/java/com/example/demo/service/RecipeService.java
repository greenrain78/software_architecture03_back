package com.example.demo.service;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeIngredient;
import com.example.demo.dto.IngredientListRequest;
import com.example.demo.dto.IngredientResponse;
import com.example.demo.dto.RecipeCreateRequest;
import com.example.demo.dto.RecipeResponse;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public RecipeResponse recommendRecipe(IngredientListRequest ingredientListRequest) {
        return RecipeResponse.builder()
                .id(1L)
                .name("김치찌개")
                .description("김치찌개는 한국의 전통 음식 중 하나로, 김치와 돼지고기를 주재료로 하는 찌개이다.")
                .ingredients(List.of(
                        IngredientResponse.builder()
                                .name("김치")
                                .value("200g")
                                .build(),
                        IngredientResponse.builder()
                                .name("두부")
                                .value("1모")
                                .build()
                ))
                .build();
    }
}
