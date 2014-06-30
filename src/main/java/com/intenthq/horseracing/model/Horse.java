package com.intenthq.horseracing.model;

import com.intenthq.horseracing.enumerations.HorseType;

public class Horse {
    
	private String name;
	private HorseType horseType;
	
	public void setHorseType(HorseType horseType) {
		this.horseType = horseType;
	}
	
	public HorseType getHorseType() {
		return this.horseType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
