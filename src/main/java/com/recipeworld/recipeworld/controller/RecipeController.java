package com.recipeworld.recipeworld.controller;

import com.recipeworld.recipeworld.dto.RecipeRequest;
import com.recipeworld.recipeworld.dto.RecipeResponse;
import com.recipeworld.recipeworld.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getRecipes() {
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> addRecipe(@RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.addRecipe(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable String id) {
        Optional<RecipeResponse> recipe = recipeService.getRecipe(id);
        return recipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
