package com.scripts.controll.dashboard.scriptsController.controller;

import java.util.List;

public class numbersInsertionResponse {

	private List<String> insertedNumbers;
	private String state;
	
	public List<String> getInsertedNumbers() {
		return insertedNumbers;
	}
	public void setInsertedNumbers(List<String> insertedNumbers) {
		this.insertedNumbers = insertedNumbers;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
