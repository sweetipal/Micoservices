package com.sweeti.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "about")
	private String about;

	public Hotel() {
	}

	public Hotel(String id, String name, String location, String about) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.about = about;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	
}
