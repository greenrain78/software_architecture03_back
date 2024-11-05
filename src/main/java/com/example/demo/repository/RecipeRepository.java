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

//    @Query("SELECT r FROM Recipe r JOIN r.recipeIngredients ri " +
//            "WHERE ri.ingredient.name IN :ingredientMap.keys " +
//            "AND ri.amount <= :ingredientMap[ri.ingredient.name] " +
//            "GROUP BY r.id " +
//            "HAVING COUNT(ri) = :ingredientMap.size")
//    List<Recipe> findRecipesByIngredients(
//            @Param("ingredientMap") Map<String, Double> ingredientMap);

    @Query(value = "SELECT r.* FROM recipe r " +
            "JOIN recipe_ingredient ri ON r.id = ri.recipe_id " +
            "WHERE ri.ingredient.name IN (:ingredientNames) " +
            "AND ri.amount <= :ingredientQuantities " +
            "GROUP BY r.id " +
            "HAVING COUNT(ri.ingredient.name) = :ingredientCount",
            nativeQuery = true)
    List<Recipe> findRecipesByIngredients(
            @Param("ingredientNames") List<String> ingredientNames,
            @Param("ingredientQuantities") Map<String, Double> ingredientQuantities,
            @Param("ingredientCount") int ingredientCount);

}

