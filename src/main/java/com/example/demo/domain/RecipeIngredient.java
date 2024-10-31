package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ingredient_id")
    @EqualsAndHashCode.Include
    private Ingredient ingredient;

    // 레시피에서 필요한 식재료의 수량
    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String unit;

    public String getValue() {
        return amount + unit;
    }
}
