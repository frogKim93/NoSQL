package com.recipeworld.recipeworld.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    private String id;
    private String name;
    private List<String> ingredients;
    private String instructions;
}
