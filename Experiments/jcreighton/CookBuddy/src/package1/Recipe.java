package package1;

import java.util.ArrayList;

public class Recipe {
	
	/**
	 * Name of the recipe
	 */
	private String name;
	
	/**
	 * List of ingredients in this recipe
	 */
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	
	private int numIngr;
	
	public Recipe(String recipeName) {
		this.name = recipeName;
		this.numIngr = 0;
	}
	
	/**
	 * 
	 * @param ingr - The ingredient to be added to the recipe
	 * @param measurement how the ingredient is being measured (grams, tsp., ounces)
	 */
	public void addIngredient(Ingredient ingr, int amount) {
		ingr.setAmount(amount);
		ingredients.add(ingr);
		numIngr++;
	}
	
	//returns the name of the recipe
	public String getName() {
		return name;
	}
	
	//returns the number of ingredients in the recipe
	public int getIngrCount() {
		return numIngr;
	}
	
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	
	//Prints out the recipe
	public String toString() {
		String recipeString = name + "\n";
		
		for(int i = 0; i < ingredients.size(); i++) {
			recipeString += ingredients.get(i).toString() + '\n';
		}
		return recipeString;
	}
	
}
