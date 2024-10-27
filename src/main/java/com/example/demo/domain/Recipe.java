package com.example.demo.domain;

import com.example.demo.dto.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private String ingredients;

    @Builder
    public Recipe(Long id, String name, String description, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        setIngredients(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return Arrays.stream(ingredients.split(","))
                .map(ingredient -> {
                    String[] ingredientInfo = ingredient.split(":");
                    return Ingredient.builder()
                            .name(ingredientInfo[0])
                            .amount(ingredientInfo[1])
                            .build();
                }).toList();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = String.valueOf(ingredients.stream().map(ingredient -> ingredient.getName() + ": " + ingredient.getAmount()).toList());
    }
}
