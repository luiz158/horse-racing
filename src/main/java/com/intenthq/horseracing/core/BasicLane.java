package com.intenthq.horseracing.core;

import com.google.common.base.Preconditions;

public class BasicLane implements Lane {
	
	private final Integer number;
	private final Horse horse;
	
	private Integer yeards;
	
	public BasicLane(Integer number, Horse horse) {
		Preconditions.checkNotNull(number);
		Preconditions.checkArgument(
				number >= MIN_LANE_NUMBER && number <= MAX_LANE_NUMBER);
		Preconditions.checkNotNull(horse);
		
		this.number = number;
		this.horse = horse;
		this.yeards = 0;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public Horse getHorse() {
		return this.horse;
	}

	@Override
	public int getYards() {
		return this.yeards;
	}
	
	@Override
	public int hitHole(Hole hole) {
		Preconditions.checkNotNull(hole);
		
		this.yeards = this.yeards + hole.getValue();
		
		return this.yeards;
	}

}
