package com.intenthq.horseracing.core;

import junit.framework.Assert;

import org.junit.Test;

public class LaneTest {
	
	@Test(expected = NullPointerException.class)
	public void cannotCreateWithNullArguments() {
		new BasicLane(null, null);
		Assert.fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateWithOutOfRangeNumber() {
		new BasicLane(8, new Horse("AA"));
		Assert.fail();
	}

	@Test
	public void canCreate() {
		Horse horse = new Horse("AA");
		BasicLane lane = new BasicLane(5, horse);
		
		Assert.assertSame(horse, lane.getHorse()); 
		Assert.assertEquals(5, lane.getNumber());
		Assert.assertEquals(0, lane.getYards());
	}
	
	@Test
	public void canHitHole() {
		BasicLane lane = new BasicLane(5, new Horse("AA"));
		
		lane.hitHole(Hole.HOLE_10);
		lane.hitHole(Hole.HOLE_20);
		lane.hitHole(Hole.HOLE_40);
		
		Assert.assertEquals(70, lane.getYards());
	}
	
}
