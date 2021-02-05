package com.demo2.backend;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Ingredients")
public class RecipeIngredient {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	private String name;	
	
	private String amount;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Recipe recipe;
	
	private int user_id = 0;
	
	/**
	 * Sets the recipe Ingredient name
	 * @param n Recipe Ingredient name
	 */
	public void setName (String n) {
		name = n;
	}
	
	/**
	 * return the Recipe Ingredient's name
	 * @return Recipe Ingredient name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the amount of the Recipe Ingredient
	 * @param i amount
	 */
	public void setAmount (String i) {
		amount = i;
	}
	
	/**
	 * returns the amount of the Recipe Ingredient
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}
	
	/**
	 * gets the id of the recipe Ingredient
	 * @return id of Recipe Ingredient
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id of the recipe Ingredient
	 * @param id id
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * gets the recipe that has this ingredient
	 * @return recipe
	 */
	public Recipe getRecipe () {
		return recipe;
	}
	
	/**
	 * sets the recipe that will have this ingredient
	 * @param r recipe
	 */
	public void setRecipe (Recipe r) {
		recipe = r;
	}
}
