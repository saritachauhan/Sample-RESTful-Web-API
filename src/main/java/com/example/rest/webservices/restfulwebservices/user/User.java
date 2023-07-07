package com.example.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;


@Entity(name="user_details")
public class User {
	
	
	protected User()  {
		
	}



	@Id
	@GeneratedValue
	private int id;
	
//	@JsonProperty("user_name")
	private String name;
	
	@Past(message="Birth date should be in past")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Post> posts;
	
	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}



	public List<Post> getPosts() {
		return posts;
	}



	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
	
	

}
