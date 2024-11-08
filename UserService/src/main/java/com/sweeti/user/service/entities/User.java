package com.sweeti.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "micro_users")
public class User {
	@Id
	@Column(name = "id")
	private String userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "about")
	private String about;
	
	@Column(name = "email")
	private String email;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	

	public User() {
	}

	public User(String userId, String name, String about, String email) {
		this.userId = userId;
		this.name = name;
		this.about = about;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	
	
}
