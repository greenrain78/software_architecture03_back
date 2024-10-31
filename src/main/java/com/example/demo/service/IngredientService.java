package com.example.demo.service;

import com.example.demo.domain.Ingredient;
import com.example.demo.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public Ingredient getOrCreateIngredient(String ingredientName) {
        // 식재료가 없으면 새로 생성
        return ingredientRepository.findByName(ingredientName)
                .orElseGet(() -> {
                    // 식재료가 없으면 새로 생성
                    Ingredient newIngredient = Ingredient.builder()
                            .name(ingredientName)
                            .build();
//                    return ingredientRepository.save(newIngredient);
                    return newIngredient;
                });
    }
}
