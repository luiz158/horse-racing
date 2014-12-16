package com.intenthq.horseracing.stubs;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.io.RaceInputParser.RaceInputProcessor;

public class RaceInputProcessorStub implements RaceInputProcessor {
	
	public List<String> names = null;
	public List<HoleHit> hits = Lists.newArrayList();
	
	@Override
	public void processHorseNames(List<String> names) {
		Preconditions.checkState(this.names == null);
		this.names = names;
	}
	
	@Override
	public void processHoleHit(Integer laneNumber, Hole hole) {
		Preconditions.checkState(this.names != null);
		this.hits.add(new HoleHit(laneNumber, hole));
	}
	
}