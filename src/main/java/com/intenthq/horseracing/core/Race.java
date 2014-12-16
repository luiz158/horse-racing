package com.intenthq.horseracing.core;

import java.util.List;

/**
 * Interface to implement a Race.
 */
public interface Race {
	
	/**
	 * Indicate that an hole have been hit.
	 * @param laneNumber the number of the lane.
	 * @param hole the hole type.
	 * @return true if successfully hit.
	 */
	boolean hitHole(Integer laneNumber, Hole hole);
	
	/**
	 * @return true if the race is terminated.
	 */
	boolean isTerminated();
	
	/**
	 * @return the list of lane in score order.
	 */
	public List<Lane> getResults();

}
