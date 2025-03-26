package com.recipeworld.recipeworld.service;

import com.recipeworld.recipeworld.dto.RecipeRequest;
import com.recipeworld.recipeworld.dto.RecipeResponse;
import com.recipeworld.recipeworld.entity.Recipe;
import com.recipeworld.recipeworld.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_PREFIX = "recipe:";

    public List<RecipeResponse> getRecipes() {
        return recipeRepository.findAll().stream().map(RecipeResponse::fromEntity).toList();
    }

    public RecipeResponse addRecipe(RecipeRequest request) {
        Recipe savedRecipe = recipeRepository.save(RecipeRequest.toEntity(request));
        redisTemplate.opsForValue().set(CACHE_PREFIX + savedRecipe.getId(), savedRecipe, 10, TimeUnit.MINUTES);
        return RecipeResponse.fromEntity(savedRecipe);
    }

    public Optional<RecipeResponse> getRecipe(String id) {
        String cacheKey = CACHE_PREFIX + id;
        Recipe cachedRecipe = (Recipe) redisTemplate.opsForValue().get(cacheKey);

        if (cachedRecipe != null) {
            return Optional.of(RecipeResponse.fromEntity(cachedRecipe));
        }

        Optional<Recipe> recipe = recipeRepository.findById(id);
        recipe.ifPresent(r -> redisTemplate.opsForValue().set(cacheKey, r, 10, TimeUnit.MINUTES));

        return recipe.map(RecipeResponse::fromEntity);
    }
}
