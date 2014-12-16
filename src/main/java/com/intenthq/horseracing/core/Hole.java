package com.intenthq.horseracing.core;

public enum Hole {
	HOLE_5(5),
	HOLE_10(10),
	HOLE_20(20),
	HOLE_40(40),
	HOLE_60(60);
	
	private int value;
	
	private Hole(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static Hole fromString(String s) {
		switch (s) {
		case "5":
			return HOLE_5;
		case "10":
			return HOLE_10;
		case "20":
			return HOLE_20;
		case "40":
			return HOLE_40;
		case "60":
			return HOLE_60;
		default:
			return null;
		}
	}
}