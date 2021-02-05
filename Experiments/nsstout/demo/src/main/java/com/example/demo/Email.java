package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity // This tells Hibernate to make a table out of this class
public class Email {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String email;
    
    @ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}
}