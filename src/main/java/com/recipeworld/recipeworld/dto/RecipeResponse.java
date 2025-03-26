package com.recipeworld.recipeworld.dto;

import com.recipeworld.recipeworld.entity.Recipe;
import lombok.Data;

import java.util.List;

@Data
public class RecipeResponse {
    private String id;
    private String name;
    private List<String> ingredients;
    private String instructions;

    public static RecipeResponse fromEntity(Recipe recipe) {
        RecipeResponse response = new RecipeResponse();
        response.setId(recipe.getId());
        response.setName(recipe.getName());
        response.setIngredients(recipe.getIngredients());
        response.setInstructions(recipe.getInstructions());
        return response;
    }
}
