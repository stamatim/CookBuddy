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

@Entity
@Table(name="Recipes")
public class Recipe {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String recipeName;
	
	private String instructions;
	
	/**
	 * Sets the user for the recipe
	 * @param u the user
	 */
	public void setUser (User u) {
		user = u;
	}
	
	/**
	 * returns the user for the recipe
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the recipe name 
	 * @param n the recipe name
	 */
	public void setRecipeName (String n) {
		recipeName = n;
	}
	
	/**
	 * returns the recipe name
	 * @return the recipe name
	 */
	public String getRecipeName() {
		return recipeName;
	}
	
	/**
	 * sets the instructions
	 * @param n the instructions
	 */
	public void setInstructions (String n) {
		instructions = n;
	}
	
	/**
	 * returns the instructions
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * returns the recipe's id
	 * @return the recipe's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the recipe's id
	 * @param id recipes id
	 */
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
}
