package com.demo2.backend;

import java.util.HashMap;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

public class Response {
	public static Map<String, String> failed () {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "309");
		ret.put("Response", "Entry Refused");
		return ret;
	}
	
	public static Map<String, String> success (int i) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("Response", "Success!");
		ret.put("userId", new Integer(i).toString());
		return ret;
	}
	
	public static Map<String, String> user (User u) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("name", u.getName());
		ret.put("userId", new Integer(u.getID()).toString());
		return ret;
	}
	
	public static Map<String, String> recipe (Recipe r) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("recipeId",  new Integer(r.getId()).toString());
		ret.put("recipeName", r.getRecipeName());
		ret.put("instructions", r.getInstructions());
		ret.putAll(user(r.getUser()));
		return ret;
	}
	
	public static Map<String, String> recipeIng (RecipeIngredient r) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("ingredientId",  new Integer(r.getId()).toString());
		ret.put("ingredientName", r.getName());
		ret.put("amount", r.getAmount());
		return ret;
	}
	
	public static Map<String, String> item (Item i) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("Error", "0");
		ret.put("itemId",  new Integer(i.getId()).toString());
		ret.put("itemName", i.getName());
		ret.put("notes", i.getNotes());
		return ret;
	}
}
