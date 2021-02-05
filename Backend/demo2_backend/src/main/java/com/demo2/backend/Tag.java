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
public class Tag {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	private String name;	
	
	private String amount;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Recipe recipe;
	
	/**
	 * sets the name of the tag
	 * @param n name
	 */
	public void setName (String n) {
		name = n;
	}
	
	/**
	 * get the name of the tag
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the amount of the tag
	 * @param i amount
	 */
	public void setAmount (String i) {
		amount = i;
	}
	
	/**
	 * gets the amount of the tag
	 * @return amount
	 */
	public String getAmount() {
		return amount;
	}
	
	/**
	 * gets the id of the tag
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the id of the tag
	 * @param id id of the tag
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * get the recipe that this is a tag of
	 * @return recipe
	 */
	public Recipe getRecipe () {
		return recipe;
	}
	
	/**
	 * sets the recipe that this is a tag of
	 * @param r recipe
	 */
	public void setRecipe (Recipe r) {
		recipe = r;
	}
}
