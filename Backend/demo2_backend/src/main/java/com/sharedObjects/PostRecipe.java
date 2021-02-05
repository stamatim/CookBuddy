package com.sharedObjects;

public class PostRecipe {
	private String recipeName;
	
	/**
	 * sets the recipe name to given string
	 * @param n recipe name
	 */
	public void setName (String n) {
		recipeName = n;
	}
	
	/**
	 * gets the recipe's name
	 * @return recipe's name
	 */
	public String getName() {
		return recipeName;
	}
	
}
