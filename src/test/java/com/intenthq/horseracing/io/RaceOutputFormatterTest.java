package com.intenthq.horseracing.io;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.intenthq.horseracing.core.Horse;
import com.intenthq.horseracing.core.Lane;
import com.intenthq.horseracing.core.Race;
import com.intenthq.horseracing.stubs.LaneStub;
import com.intenthq.horseracing.stubs.RaceStub;

public class RaceOutputFormatterTest {
	
	private final static String NORMAL_OUTPUT = 
			RaceOutputFormatter.HEADER + "\n" +
			"1, 3, C\n" +
			"2, 1, A\n" +
			"3, 2, B";
	
	@Test
	public void canFormatNormalResults() {
		Race raceStub = new RaceStub(true, Arrays.<Lane>asList(
				new LaneStub(220, 3, new Horse("C")),
				new LaneStub(40, 1, new Horse("A")),
				new LaneStub(20, 2, new Horse("B"))));
		
		String output = RaceOutputFormatter.format(raceStub);
		Assert.assertEquals(NORMAL_OUTPUT, output);
		
	}
	
	@Test
	public void canFormatNotTerminated() {
		Race raceStub = new RaceStub(false, Arrays.<Lane>asList());
		
		String output = RaceOutputFormatter.format(raceStub);
		Assert.assertEquals(RaceOutputFormatter.NOT_TERMINATED, output);
		
	}

}
