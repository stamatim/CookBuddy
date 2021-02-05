package com.demo2.backend;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
/**
 * 
 * @author Nicholas Stout
 *
 *User object. Has three variables
 *
 *name: The name of the user.
 *email: The email of the user.
 *password: The users password.
 */
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id=-1;
	
	private String name;
	private String email;
	private String password;
	
	/**
	 * Sets the name of the user
	 * @param n name of user
	 */
	public void setName (String n) {
		name = n;
	}
	
	/**
	 * returns the name of the user
	 * @return name of user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * gets the id of the user
	 * @return id of user
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * sets the id of the user
	 * @param id the user's new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * sets the email of the user
	 * @param e user's new email
	 */
	public void setEmail(String e) {
		email = e;
	}
	
	/**
	 * gets the user's email
	 * @return the user's email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * sets the user's password
	 * @param p the user's new password
	 */
	public void setPassword(String p) {
		password = p;
	}
	
	/**
	 * returns the user's password
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}
}
