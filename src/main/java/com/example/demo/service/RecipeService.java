package com.example.demo.service;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeIngredient;
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
        log.warn("레시피 생성 요청 : {}", recipeCreateRequest);
        Recipe recipe = Recipe.builder()
                .name(recipeCreateRequest.getName())
                .description(recipeCreateRequest.getDescription())
                .build();
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();
        log.warn("레시피 생성 요청 recipe : {}", recipe);

        // Step 2. 각 재료 요청에 대해 반복 처리
        recipeCreateRequest.getIngredients().forEach(ingredientRequest -> {
            Ingredient ingredient = ingredientRepository.findByName(ingredientRequest.getName())
                    .orElseGet(() -> Ingredient.builder()
                            .name(ingredientRequest.getName())
                            .build());
            log.warn("레시피 생성 요청 ingredientRequest : {}", ingredientRequest);
            log.warn("레시피 생성 요청 ingredient : {}", ingredient);
            RecipeIngredient recipeIngredient = RecipeIngredient.builder()
                    .recipe(recipe)
                    .ingredient(ingredient)
                    .amount(ingredientRequest.getAmount())
                    .unit(ingredientRequest.getUnit())
                    .build();
            log.warn("레시피 생성 요청 recipeIngredient : {}", recipeIngredient);
            recipeIngredients.add(recipeIngredient);
        });
        log.warn("레시피 생성 요청 recipeIngredients : {}", recipeIngredients);
        recipe.setRecipeIngredients(recipeIngredients);
        return recipeRepository.save(recipe);
    }
//        Recipe recipe = Recipe.builder()
//                .name(recipeCreateRequest.getName())
//                .description(recipeCreateRequest.getDescription())
//                .recipeIngredients(recipeCreateRequest.getIngredients().stream()
//                        .map(ingredientRequest -> {
//                            // 재료 가져오기
//                            Ingredient ingredient = ingredientService.getOrCreateIngredient(ingredientRequest.getName());
//                            // 수량 형식 검증
//                            String[] amountParts = ingredientRequest.getAmount().split(" ");
//                            if (amountParts.length != 2 || !amountParts[0].matches("\\d+")) {
//                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 수량 정보: " + ingredientRequest.getAmount());
//                            }
//                            // 수량 정보 저장
//                            return RecipeIngredient.builder()
//                                    .ingredient(ingredient)
//                                    .amount(Integer.parseInt(amountParts[0]))
//                                    .unit(amountParts[1])
//                                    .build();
//                            })
//                        .collect(Collectors.toSet())) // Set으로 변환
//                .build();
//        Recipe savedRecipe = recipeRepository.save(recipe);
//        return RecipeResponse.builder()
//                .id(savedRecipe.getId())
//                .name(savedRecipe.getName())
//                .description(savedRecipe.getDescription())
//                .ingredients(savedRecipe.getRecipeIngredients().stream()
//                        .map(savedIngredient -> IngredientResponse.builder()
//                                .name(savedIngredient.getIngredient().getName())
//                                .amount(savedIngredient.getFormattedAmount())
//                                .build())
//                        .toList())
//                .build();
//    }
//
//    public RecipeResponse updateRecipe(Long id, RecipeCreateRequest recipeCreateRequest) {
//        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 레시피가 존재하지 않습니다."));
//        recipe.setName(recipeCreateRequest.getName());
//        recipe.setDescription(recipeCreateRequest.getDescription());
//        recipe.setIngredients(recipeCreateRequest.getIngredients());
//        Recipe savedRecipe = recipeRepository.save(recipe);
//        return RecipeResponse.builder()
//                .id(savedRecipe.getId())
//                .name(savedRecipe.getName())
//                .description(savedRecipe.getDescription())
//                .ingredients(savedRecipe.getIngredients())
//                .build();
//    }
//
//    public void deleteRecipe(Long id) {
//        recipeRepository.deleteById(id);
//    }
//
//    public List<RecipeResponse> searchRecipe(String keyword) {
//        List<Recipe> recipeList = recipeRepository.findByName(keyword);
//        return recipeList.stream().map(recipe -> RecipeResponse.builder()
//                .id(recipe.getId())
//                .name(recipe.getName())
//                .description(recipe.getDescription())
//                .ingredients(recipe.getIngredients())
//                .build()).toList();
//    }


//    public RecipeResponse recommendRecipe(IngredientListRequest ingredientListRequest) {
//        return RecipeResponse.builder()
//                .id(1L)
//                .name("김치찌개")
//                .description("김치찌개는 한국의 전통 음식 중 하나로, 김치와 돼지고기를 주재료로 하는 찌개이다.")
//                .ingredients(ingredientListRequest.getIngredients())
//                .build();
//    }
}
