package com.demo2.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecipeIngredientController {
	@Autowired
	private RecipeIngredientRepository RI_Repo;
	
	@Autowired
	private RecipeRepository R_Repo;
	
	@Autowired
	private UserRepository U_Repo;
	
	/**
	 * Adds a recipeIngredient to a recipe
	 * @param userID Id of the user to be associated with recipe
	 * @param recipeID id of the recipe to get added to
	 * @param n json object containing recipe information
	 * @return success or failure json response
	 */
	@PostMapping(path="/recipesIng/{user_id}/{recipe_id}/add", consumes= "application/json")
	public @ResponseBody Map<String, String> addNewIngredient (@PathVariable (value = "user_id") int userID, @PathVariable (value = "recipe_id") int recipeID, @RequestBody RecipeIngredient n) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		if (r.isPresent()) {
			n.setRecipe(r.get());
			RI_Repo.save(n);
			return Response.recipeIng(n);
		}
		return Response.failed();
	}
	
	/**
	 * Adds a list of recipeIngredients to a recipe
	 * @param recipeID recipe id to be added to
	 * @param n json object list of recipe Ingredients
	 * @return success or failure json object
	 */
	@PostMapping(path="/recipesIng/{recipe_id}/addall", consumes= "application/json")
	public @ResponseBody Map<String, String> addNewIngredients (@PathVariable (value = "recipe_id") int recipeID, @RequestBody List<RecipeIngredient> n) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		if(!r.isPresent())
		{
			return Response.failed();
		}
		
		ListIterator<RecipeIngredient> iter = n.listIterator();
		if (!iter.hasNext()) {
			return Response.failed();
		}
		while (iter.hasNext()) {
			RecipeIngredient tmp = iter.next();
			tmp.setRecipe(r.get());
			RI_Repo.save(tmp);
		}
		return Response.success(1);
	}
	
	/**
	 * returns a list of recipe Ingredients for a given recipe
	 * @param recipe_id id of recipe to get ingredients of
	 * @return json object containing information of all recipe Ingredients
	 */
	@GetMapping(path="/recipesIng/{recipe_id}/all")
	public @ResponseBody ArrayList<Map<String,String>> getAllIngredients(@PathVariable (value="recipe_id") int recipe_id) {
		// This returns a JSON or XML with the users
		Iterable<RecipeIngredient> ri = RI_Repo.findByRecipeId(recipe_id);
		ArrayList<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		Iterator<RecipeIngredient> i =ri.iterator();
		if (!i.hasNext()) {
			ret.add(Response.failed());
			return ret;
		}
		while (i.hasNext()) {
			ret.add(Response.recipeIng(i.next()));
		}
		return ret;
	}
	
//	@GetMapping(path="/recipesIng/{recipe_id}/get_by_id")
//	public @ResponseBody String get_by_id (@RequestParam String id) {
//		int i = Integer.parseInt(id);
//		Optional<RecipeIngredient> u = RI_Repo.findById(i);
//		return u.get().getName();
//	}
	
	/**
	 * removes a recipe ingredient with given id
	 * @param id the id of recipe ingredient to be removed
	 * @return returns "deletion successful" if the recipe ingredient is deleted
	 */
	@GetMapping(path="/recipesIng/{recipe_id}/remove_by_id")
	public @ResponseBody String remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		RI_Repo.deleteById(i);
		return "deletion successful";
	}
	
}
