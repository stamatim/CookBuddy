package com.sharedObjects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

public class PostUser {
	private String name;
	private String email;
	private String password;
	
	/**
	 * Sets the user's name 
	 * @param n new user's name
	 */
	public void setName (String n) {
		name = n;
	}
	
	/**
	 * returns the user's name
	 * @return user's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets a user's email address
	 * @param n email address
	 */
	public void setemail (String n) {
		email = n;
	}
	
	/**
	 * get's the users email address
	 * @return the email address
	 */
	public String getemail() {
		return email;
	}
	
	/**
	 * sets the user's password
	 * @param p user's password
	 */
	public void setPassword(String p) {
		password = p;
	}
}
