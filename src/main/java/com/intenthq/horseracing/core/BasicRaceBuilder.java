package com.intenthq.horseracing.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Builder to create a {@link BasicRace}.
 */
public class BasicRaceBuilder {
	
	private final List<Horse> horses;
	
	public BasicRaceBuilder() {
		this.horses = Lists.newArrayList();
	}
	
	/**
	 * Add an horse at the next lane.
	 * @param horse the horse to had.
	 * @return the builder to chain.
	 */
	public BasicRaceBuilder addHorse(Horse horse) {
		Preconditions.checkNotNull(horse);
		this.horses.add(horse);
		return this;
	}
	
	/**
	 * Create a new instance of {@link BasicRace}.
	 * @return the new instance of {@link BasicRace}.
	 */
	public Race create() {
		List<Lane> lanes = new ArrayList<Lane>(horses.size());
		for (int index = 0; index < horses.size(); index++) {
			Horse horse = horses.get(index);
			lanes.add(new BasicLane(index + 1, horse));
		}
		return new BasicRace(lanes);
	}

}
