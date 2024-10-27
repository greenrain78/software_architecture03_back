package com.example.demo.repository;

import com.example.demo.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    List<Recipe> findAllByOrderByIdDesc();
}

