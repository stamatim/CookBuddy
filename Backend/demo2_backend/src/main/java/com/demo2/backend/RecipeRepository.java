package com.demo2.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.demo2.backend.Recipe;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	Iterable<Recipe> findByUserId(int userId);

}