package com.intenthq.horseracing.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class RaceTest {
	
	@Test(expected = NullPointerException.class)
	public void cannotCreateWithNullArguments() {
		new BasicRace(null);
		Assert.fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateWithSingleLane() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		
		new BasicRace(Arrays.asList(l1));
		
		Assert.fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateWithoutLane() {
		new BasicRace(Collections.<Lane>emptyList());
		
		Assert.fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateWithOutOfRangeDuplicateLane() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		Lane l2 = new BasicLane(2, new Horse("BB"));
		Lane l3 = new BasicLane(3, new Horse("CC"));
		Lane l4 = new BasicLane(3, new Horse("DD"));
		
		new BasicRace(Arrays.asList(l1, l2, l3, l4));
		
		Assert.fail();
	}

	@Test
	public void canCreate() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		Lane l2 = new BasicLane(2, new Horse("BB"));
		Lane l3 = new BasicLane(3, new Horse("CC"));
		
		BasicRace race = new BasicRace(Arrays.asList(l1, l2, l3));
		
		Assert.assertEquals(false, race.isTerminated());
	}
	
	@Test
	public void canHitHole() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		Lane l2 = new BasicLane(2, new Horse("BB"));
		Lane l3 = new BasicLane(3, new Horse("CC"));
		
		BasicRace race = new BasicRace(Arrays.asList(l1, l2, l3));
		race.hitHole(1, Hole.HOLE_10);
		race.hitHole(2, Hole.HOLE_20);
		race.hitHole(3, Hole.HOLE_40);
		
		Assert.assertFalse(race.isTerminated());
		List<Lane> result = race.getResults();
		Assert.assertEquals(3, result.size());
		Assert.assertSame(l3, result.get(0));
		Assert.assertSame(l2, result.get(1));
		Assert.assertSame(l1, result.get(2));
	}
	
	@Test
	public void canHitHoleAndReachTheEnd() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		Lane l2 = new BasicLane(2, new Horse("BB"));
		Lane l3 = new BasicLane(3, new Horse("CC"));
		
		Race race = new BasicRace(Arrays.asList(l1, l2, l3));
		race.hitHole(1, Hole.HOLE_60);
		race.hitHole(1, Hole.HOLE_60);
		race.hitHole(1, Hole.HOLE_60);
		race.hitHole(1, Hole.HOLE_60);
		
		List<Lane> result = race.getResults();
		Assert.assertTrue(race.isTerminated());
		Assert.assertEquals(l1, result.get(0));
	}
	
	@Test
	public void canHitEmptyHoleWithoutError() {
		Lane l1 = new BasicLane(1, new Horse("AA"));
		Lane l2 = new BasicLane(2, new Horse("BB"));
		
		Race race = new BasicRace(Arrays.asList(l1, l2));
		race.hitHole(3, Hole.HOLE_60);
		
		List<Lane> result = race.getResults();
		Assert.assertFalse(race.isTerminated());
		Assert.assertEquals(0, result.get(0).getYards());
	}
	
}
