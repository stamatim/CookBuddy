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
public class Item {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;	
	
	private String name;	
	
	private String notes;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	/**
	 * Sets the item's name 
	 * @param n the item's name
	 */
	public void setName (String n) {
		name = n;
	}
	
	/**
	 * returns the item's name
	 * @return the item's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets notes for the item
	 * @param i notes for the item
	 */
	public void setNotes (String i) {
		notes = i;
	}
	
	/**
	 * returns the item's notes
	 * @return item's notes
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * returns the item's id
	 * @return the item's id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the item's id
	 * @param id the item's id
	 */
	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * returns the user associated with the item
	 * @return user
	 */
	public User getUser () {
		return user;
	}
	
	/**
	 * sets a user for the item
	 * @param u the user
	 */
	public void setUser (User u) {
		user = u;
	}
}
