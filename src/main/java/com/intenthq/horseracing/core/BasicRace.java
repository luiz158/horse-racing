package com.intenthq.horseracing.core;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class BasicRace implements Race {
	
	public static final Integer YEARDS_GOAL = 220;
	
	private static final Ordering<Lane> ORDERING_LANE_BY_POSITION = Ordering.from(new Comparator<Lane>() {
		@Override
		public int compare(Lane lane1, Lane lane2) {
			return Ints.compare(lane1.getYards(), lane2.getYards());
		}
	}).reverse();
	
	private final Map<Integer, Lane> lanes;
	
	private boolean terminated = false;

	public BasicRace(List<Lane> lanes) {
		Preconditions.checkNotNull(lanes);
		Preconditions.checkArgument(lanes.size() > 1);
		
		this.lanes = Maps.uniqueIndex(lanes, new Function<Lane, Integer>() {
			@Override
			public Integer apply(Lane lane) {
				return lane.getNumber();
			}
		});
	}
	
	@Override
	public boolean hitHole(Integer laneNumber, Hole hole) {
		Preconditions.checkNotNull(hole);
		Preconditions.checkArgument(laneNumber >= Lane.MIN_LANE_NUMBER);
		Preconditions.checkArgument(laneNumber <= Lane.MAX_LANE_NUMBER);
		
		if(terminated) {
			return false;
		}
		
		Lane lane = this.lanes.get(laneNumber);
		
		if(lane != null) {
			int yeards = lane.hitHole(hole);
			
			if(yeards >= YEARDS_GOAL) {
				terminated = true;
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isTerminated() {
		return terminated;
	}
	
	@Override
	public List<Lane> getResults() {
		return ORDERING_LANE_BY_POSITION.sortedCopy(this.lanes.values());
	}
	
}
