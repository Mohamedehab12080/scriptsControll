package com.scripts.controll.dashboard.scriptsController.controller;

import java.util.ArrayList;
import java.util.List;

import com.scripts.controll.dashboard.scriptsController.model.cokies;

public class cokiesRequest {

	private List<String> cookiesList;

	public List<String> getcookiesList() {
		return cookiesList;
	}

	public void setCokiesList(List<String> cookiesList) {
		
		this.cookiesList = cookiesList;
	}
	
	public List<cokies> setAndGetCokiesObject()
	{
		return mapToLists(getcookiesList());
	}
	
	private List<cokies> mapToLists(List<String> input)
	{
		List<cokies> cokiesListt=new ArrayList<cokies>();
		for(String iputName:input)
		{
			cokies cokiesInput=new cokies();
			cokiesInput.setCokies(iputName.strip());
			cokiesListt.add(cokiesInput);
		}
		return cokiesListt;
	}

}
