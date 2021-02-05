package com.demo2.backend;

import java.util.List;
import java.util.ListIterator;
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
public class TagController {
	@Autowired
	private TagRepository T_Repo;
	
	@Autowired
	private RecipeRepository R_Repo;
	
	/**
	 * Adds a tag
	 * @param recipeID id of recipe to add the tag to
	 * @param n json object of tag
	 * @return id of tag
	 */
	@PostMapping(path="/tags/{recipe_id}/add", consumes= "application/json")
	public @ResponseBody int addNewIngredient (@PathVariable (value = "recipe_id") int recipeID, @RequestBody RecipeIngredient n) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		n.setRecipe(r.get());
		T_Repo.save(n);
		return n.getId();
	}
	
	/**
	 * adds multiple tags to a recipe at once
	 * @param recipeID id of the recipe to add to
	 * @param n json object containing data of the tags
	 * @return returns 1 if successful
	 */
	@PostMapping(path="/tags/{recipe_id}/addall", consumes= "application/json")
	public @ResponseBody int addNewIngredients (@PathVariable (value = "recipe_id") int recipeID, @RequestBody List<RecipeIngredient> n) {
		Optional<Recipe> r = R_Repo.findById(recipeID);
		ListIterator<RecipeIngredient> iter = n.listIterator();
		while (iter.hasNext()) {
			RecipeIngredient tmp = iter.next();
			tmp.setRecipe(r.get());
			T_Repo.save(tmp);
		}
		return 1;
	}
	
	/**
	 * gets all tags for a recipe
	 * @param recipe_id id of the recipe to get tags of
	 * @return json object of tags
	 */
	@GetMapping(path="/tags/{recipe_id}/all")
	public @ResponseBody Iterable<Tag> getAllIngredients(@PathVariable (value="recipe_id") int recipe_id) {
		// This returns a JSON or XML with the users
		return T_Repo.findByRecipeId(recipe_id);
		//return RI_Repo.findAll();
	}
	
	/**
	 * gets the tag with the given id
	 * @param id id of the tag to get
	 * @return json object of tag
	 */
	@GetMapping(path="/tags/{recipe_id}/get_by_id")
	public @ResponseBody String get_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		Optional<RecipeIngredient> u = T_Repo.findById(i);
		return u.get().getName();
	}
	
	/**
	 * removes the tag with the given id
	 * @param id id of the tag to remove
	 * @return "deletion successful" if the tag is removed
	 */
	@GetMapping(path="/tags/{recipe_id}/remove_by_id")
	public @ResponseBody String remove_by_id (@RequestParam String id) {
		int i = Integer.parseInt(id);
		T_Repo.deleteById(i);
		return "deletion successful";
	}
	
}
