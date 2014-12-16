package com.intenthq.horseracing.stubs;

import java.util.List;

import com.google.common.collect.Lists;
import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.core.Lane;
import com.intenthq.horseracing.core.Race;

public final class RaceStub implements Race {
	
	public boolean terminated;
	public List<HoleHit> hits = Lists.newArrayList();
	public List<Lane> results;
	
	public RaceStub(boolean terminated, List<Lane> results) {
		this.terminated = terminated;
		this.results = results;
	}

	@Override
	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public boolean hitHole(Integer laneNumber, Hole hole) {
		hits.add(new HoleHit(laneNumber, hole));
		return true;
	}

	@Override
	public List<Lane> getResults() {
		return results;
	}
}