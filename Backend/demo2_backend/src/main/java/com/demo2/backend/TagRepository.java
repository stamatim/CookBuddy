package com.demo2.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.demo2.backend.RecipeIngredient;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TagRepository extends JpaRepository<RecipeIngredient, Integer> {
	Iterable<Tag> findByRecipeId(int RecipeId);
}