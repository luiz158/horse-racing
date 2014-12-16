package com.intenthq.horseracing.core;

/**
 * Interface to implement a Lane.
 */
public interface Lane {
	
	static final Integer MIN_LANE_NUMBER = 1;
	static final Integer MAX_LANE_NUMBER = 7;
	
	/**
	 * @return the number of the lane.
	 */
	int getNumber();

	/**
	 * @return the horse information.
	 */
	Horse getHorse();

	/**
	 * @return the amount of yards already traversed.
	 */
	int getYards();
	
	/**
	 * Indicate that a hole has been hit on this lane.
	 * @param hole the hole type.
	 * @return the amount of yards already traversed.
	 */
	int hitHole(Hole hole);

}
