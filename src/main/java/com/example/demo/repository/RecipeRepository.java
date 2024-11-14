package com.example.demo.repository;

import com.example.demo.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    List<Recipe> findAllByOrderByIdDesc();

    List<Recipe> findByNameContaining(String recipeName);
}

