package com.intenthq.horseracing.processor;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.intenthq.horseracing.core.Hole;

public class RaceProcessorTest {
	
	@Test
	public void canProcessHorseNames() {
		RaceProcessor raceProcessor = new RaceProcessor();
		
		raceProcessor.processHorseNames(Arrays.asList("A", "B", "C"));
		
		Assert.assertNotNull(raceProcessor.getRace());
		Assert.assertEquals(1, raceProcessor.getRace().getResults().get(0).getNumber());
		Assert.assertEquals(2, raceProcessor.getRace().getResults().get(1).getNumber());
		Assert.assertEquals(3, raceProcessor.getRace().getResults().get(2).getNumber());
		Assert.assertEquals("A", raceProcessor.getRace().getResults().get(0).getHorse().getName());
		Assert.assertEquals("B", raceProcessor.getRace().getResults().get(1).getHorse().getName());
		Assert.assertEquals("C", raceProcessor.getRace().getResults().get(2).getHorse().getName());
	}
	
	@Test
	public void canProcessHoleHit() {
		RaceProcessor raceProcessor = new RaceProcessor();
		
		raceProcessor.processHorseNames(Arrays.asList("A", "B"));
		raceProcessor.processHoleHit(1, Hole.HOLE_10);
		raceProcessor.processHoleHit(1, Hole.HOLE_20);
		
		Assert.assertNotNull(raceProcessor.getRace());
		Assert.assertEquals(1, raceProcessor.getRace().getResults().get(0).getNumber());
		Assert.assertEquals("A", raceProcessor.getRace().getResults().get(0).getHorse().getName());
		Assert.assertEquals(30, raceProcessor.getRace().getResults().get(0).getYards());
	}

}
