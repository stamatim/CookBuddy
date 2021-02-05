package package1;

public class Testing {
	public static void main(String args[]) {
		Ingredient ingr1 = new Ingredient("bread", "slice");
		Ingredient ingr2 = new Ingredient("cheese", "slice");
		
		Recipe grilledCheese = new Recipe("Grilled Cheese");
		
		grilledCheese.addIngredient(ingr1, 2);
		grilledCheese.addIngredient(ingr2, 2);
		
		//System.out.println(grilledCheese.toString());
		
		Ingredient lemon = new Ingredient("Lemon", "wedge");
		Ingredient lime = new Ingredient("Lime", "wedge");
		Ingredient vodka = new Ingredient("Vodka", "oz.");
		Ingredient tomatoJuice = new Ingredient("Tomato Juice", "oz.");
		Ingredient tabasco = new Ingredient("Tabasco Sauce", "dashes");
		Ingredient horseradish = new Ingredient("Horseradish", "tsp.");
		Ingredient worcestershire = new Ingredient("Worcestershire", "dashes");
		Ingredient celerySalt = new Ingredient("Celery Salt", "pinch");
		Ingredient pepper = new Ingredient("Pepper", "pinch");
		Ingredient paprika = new Ingredient("Paprika", "pinch");
		
		Recipe bloodyMary = new Recipe("Bloody Mary");
		bloodyMary.addIngredient(vodka, 2);
		bloodyMary.addIngredient(lemon, 1);
		bloodyMary.addIngredient(lime, 1);
		bloodyMary.addIngredient(tomatoJuice, 4);
		bloodyMary.addIngredient(tabasco, 2);
		bloodyMary.addIngredient(worcestershire, 2);
		bloodyMary.addIngredient(horseradish, 2);
		bloodyMary.addIngredient(celerySalt, 1);
		bloodyMary.addIngredient(pepper, 1);
		bloodyMary.addIngredient(paprika, 1);
		
		//System.out.println(bloodyMary.toString());
		
		CookBook testBook = new CookBook("Testing Cook Book");
		testBook.addRecipe(bloodyMary);
		testBook.addRecipe(grilledCheese);
		
		System.out.println(testBook.shoppingList());
		
	}
}
