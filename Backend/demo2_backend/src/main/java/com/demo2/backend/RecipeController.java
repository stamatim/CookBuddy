package com.demo2.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import com.demo2.backend.User;
import com.demo2.backend.UserRepository;
import com.demo2.backend.Response;

@Controller
@RequestMapping(path="/recipes")
public class RecipeController {
	@Autowired
	private RecipeRepository RRepo;
	@Autowired
	private UserRepository URepo;
	
	/**
	 * adds a recipe 
	 * @param userID id of user associated with recipe
	 * @param recipe json object containing information of the recipe
	 * @return failure or success json object
	 */
	@PostMapping(path= "/{user_id}/add", consumes = "application/json")
	public @ResponseBody Map<String,String> addNewRecipe (@PathVariable (value = "user_id") int userID, @RequestBody Recipe recipe) {
		Optional<User> u = URepo.findById(userID);
		if (u.isPresent()) {
			recipe.setUser(u.get());
			RRepo.save(recipe);
			return Response.recipe(recipe);
		} else {
			return Response.failed();
		}
	}
	
	/**
	 * returns all recipes of a given user
	 * @param userID id of the user to get recipes of
	 * @return json object containing all recipe info for that user
	 */
	@GetMapping(path="/{user_id}/all")
	public @ResponseBody List<Map<String,String>> getRecipesByUserId(@PathVariable (value = "user_id") int userID) {
		Iterator<Recipe> i = RRepo.findByUserId(userID).iterator();
		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		if (!i.hasNext()) {
			ret.add(Response.failed());
		}
		while (i.hasNext()) {
			ret.add(Response.recipe(i.next()));
		}
		return ret;
	}
	
	/**
	 * returns all recipes
	 * @return json object of all recipes
	 */
	@GetMapping(path="/all")
	public @ResponseBody List<Map<String,String>> getAllRecipes() {
		// This returns a JSON or XML with the users
		Iterator<Recipe> i = RRepo.findAll().iterator();
		List<Map<String,String>> ret = new ArrayList<Map<String,String>>();
		if (!i.hasNext()) {
			ret.add(Response.failed());
		}
		while (i.hasNext()) {
			ret.add(Response.recipe(i.next()));
		}
		return ret;
	}
	
	/**
	 * returns the recipe with a given id
	 * @param id the recipe's id
	 * @return json object of recipe or failure
	 */
	@GetMapping(path="/get_by_id")
	public @ResponseBody Map<String, String> get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<Recipe> r = RRepo.findById(i);
		if (r.isPresent())
			return Response.recipe(r.get());
		else {
			return Response.failed();
		}
	}
	
	/**
	 * remove the recipe with the given id
	 * @param id id of recipe to be removed
	 * @return "removal successful" if recipe is removed
	 */
	@GetMapping(path="/remove_by_id{id}")
	public @ResponseBody String deleteRecipe(@RequestParam String id) {
		int n = Integer.parseInt(id);
		RRepo.deleteById(n);
		return "removal successful";
	}
	
}
