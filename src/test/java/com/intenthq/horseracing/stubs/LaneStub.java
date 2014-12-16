package com.intenthq.horseracing.stubs;

import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.core.Horse;
import com.intenthq.horseracing.core.Lane;

public final class LaneStub implements Lane {
	
	public int yards;
	public int number;
	public Horse horse;

	public LaneStub(int yards, int number, Horse horse) {
		this.yards = yards;
		this.number = number;
		this.horse = horse;
	}

	@Override
	public Horse getHorse() {
		return horse;
	}
	
	@Override
	public int getNumber() {
		return number;
	}
	
	@Override
	public int getYards() {
		return yards;
	}
	
	@Override
	public int hitHole(Hole hole) {
		return 0;
	}
}