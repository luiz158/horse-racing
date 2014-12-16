package com.intenthq.horseracing.stubs;

import com.intenthq.horseracing.core.Hole;

public class HoleHit {
	
	public Integer laneNumber;
	public Hole hole;
	
	public HoleHit(Integer laneNumber, Hole hole) {
		this.laneNumber = laneNumber;
		this.hole = hole;
	}
}