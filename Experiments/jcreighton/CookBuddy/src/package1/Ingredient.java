package package1;

public class Ingredient {
	
	/**
	 * name of the ingredient
	 */
	private String name;
	
	/**
	 * how much of the ingredient is needed for a recipe
	 */
	private int amount;
	
	/**
	 * how the ingredient is measured (grams, tsp., ounces, etc.)
	 */
	private String measurement;
	
	/**
	 * constructor for ingredients
	 * @param ingrName name of the ingredient
	 * @param measurement how this ingredient will be measured
	 */
	
	public Ingredient(String ingrName, String measurement) {
		this.name = ingrName;
		this.measurement = measurement;
	}
	
	/**
	 * gets the name of the ingredient
	 * @return the name of the ingredient
	 */
	public String getName() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getMeasurement() {
		return measurement;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String toString() {
		String ingrString = name + ' ' + amount + ' ' + measurement;
		return ingrString;
	}
	
}
