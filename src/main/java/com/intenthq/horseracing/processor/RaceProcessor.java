package com.intenthq.horseracing.processor;

import java.util.List;

import com.google.common.base.Preconditions;
import com.intenthq.horseracing.core.BasicRaceBuilder;
import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.core.Horse;
import com.intenthq.horseracing.core.Race;
import com.intenthq.horseracing.io.RaceInputParser.RaceInputProcessor;

public class RaceProcessor implements RaceInputProcessor {
	
	private Race race;

	@Override
	public void processHorseNames(List<String> names) {
		Preconditions.checkNotNull(names);
		Preconditions.checkState(race == null);
		
		BasicRaceBuilder raceBuilder = new BasicRaceBuilder();
		for(String name : names) {
			raceBuilder.addHorse(new Horse(name));
		}
		
		this.race = raceBuilder.create();
	}

	@Override
	public void processHoleHit(Integer laneNumber, Hole hole) {
		Preconditions.checkArgument(laneNumber > 0);
		Preconditions.checkNotNull(hole);
		Preconditions.checkState(race != null);
		
		this.race.hitHole(laneNumber, hole);
	}
	
	public Race getRace() {
		Preconditions.checkState(race != null);
		
		return this.race;
	}

}
