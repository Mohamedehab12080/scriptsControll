package com.scripts.controll.dashboard.scriptsController.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class cokies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(length = 500)
	private String cokies;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCokies() {
		return cokies;
	}
	public void setCokies(String cokies) {
		this.cokies = cokies;
	}
	
	
}
