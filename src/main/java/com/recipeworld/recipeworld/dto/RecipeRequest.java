package com.recipeworld.recipeworld.dto;

import com.recipeworld.recipeworld.entity.Recipe;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRequest {
    private String name;
    private List<String> ingredients;
    private String instructions;

    public static Recipe toEntity(RecipeRequest request) {
        return Recipe.builder()
            .name(request.getName())
            .ingredients(request.getIngredients())
            .instructions(request.getInstructions())
            .build();
    }
}
