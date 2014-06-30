package com.intenthq.horseracing.model;

import com.intenthq.horseracing.model.Horse;
import com.intenthq.horseracing.model.Human;

public class HorseRacer extends Racer {
    
	private Horse horse;
	private Human human;
	
	public void setHorse(Horse horse) {
		this.horse = horse;
	}
	
	public Horse getHorse(Horse horse) {
		return horse;
	}

	public void setHuman(Human human) {
		this.human = human;
	}
	
	public Human getHuman(Human human) {
		return human;
	}
}
