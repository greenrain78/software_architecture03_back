package com.example.demo.repository;

import com.example.demo.domain.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
