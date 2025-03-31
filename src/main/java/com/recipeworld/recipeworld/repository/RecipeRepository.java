package com.recipeworld.recipeworld.repository;

import com.recipeworld.recipeworld.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
}
