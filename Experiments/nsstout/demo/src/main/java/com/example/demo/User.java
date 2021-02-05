//Followed this tutorial https://spring.io/guides/gs/accessing-data-mysql/

package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    //@OneToMany(mappedBy="User",cascade = CascadeType.ALL)
    //private List<Email> emails;
    
    private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**public List<Email> getEmail() {
		return emails;
	}**/
	
	public String getEmail() {
		return email;
	}
	
	/**public void addEmail(String email) {
		Email e = new Email();
		e.setEmail(email);
		this.emails.add(e);
	}**/
	
	public void setEmail(String email) {
		this.email = email;
	}


}