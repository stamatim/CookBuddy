package package1;

import java.util.ArrayList;

public class CookBook {
	
	/**
	 * The name of the cook book
	 */
	private String name;
	
	/**
	 * Recipes contained in this cook book
	 */
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	public CookBook(String bookName) {
		this.name = bookName;
	}
	
	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * creates a shopping list of ingredients required to make the desired recipes
	 * @return shopping list of ingredients
	 */
	public String shoppingList() {
		ArrayList<Ingredient> shoppingList = new ArrayList<Ingredient>();
		String listString = "";
		
		for(int i = 0; i < recipes.size(); i++) {
			shoppingList.addAll(recipes.get(i).getIngredients());
		}
		
		for(int i = 0; i < shoppingList.size(); i++) {
			listString += shoppingList.get(i).toString() + '\n';
		}
		
		return listString;
	}
	
}
