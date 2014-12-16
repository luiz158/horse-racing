package com.intenthq.horseracing.io;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.stubs.RaceInputProcessorStub;

public class RaceInputParserTest {

	@Test(expected = NullPointerException.class)
	public void cannotCreateParser() {
		new RaceInputParser(null);
		Assert.fail();
	}

	@Test
	public void cannotCreateRaceInputParcer() {
		new RaceInputParser(new RaceInputProcessorStub());
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseTooMuchHosrses() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4, test5, test6, test7, test8\n" +
				"1 10\n");
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseSingleHorse() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1\n" +
				"1 10\n");
	}

	@Test
	public void canParse() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"1 10\n" +
				"1 10\n" +
				"2 20\n" +
				"2 20\n" +
				"3 60");
		
		Assert.assertEquals(4, processorStub.names.size());
		Assert.assertEquals(
				Arrays.asList("test1", "test2", "test3", "test4"), 
				processorStub.names);
		Assert.assertEquals(5, processorStub.hits.size());
		Assert.assertEquals((Integer)1, processorStub.hits.get(0).laneNumber);
		Assert.assertEquals((Integer)1, processorStub.hits.get(1).laneNumber);
		Assert.assertEquals((Integer)2, processorStub.hits.get(2).laneNumber);
		Assert.assertEquals((Integer)2, processorStub.hits.get(3).laneNumber);
		Assert.assertEquals((Integer)3, processorStub.hits.get(4).laneNumber);
		Assert.assertEquals(Hole.HOLE_10, processorStub.hits.get(0).hole);
		Assert.assertEquals(Hole.HOLE_10, processorStub.hits.get(1).hole);
		Assert.assertEquals(Hole.HOLE_20, processorStub.hits.get(2).hole);
		Assert.assertEquals(Hole.HOLE_20, processorStub.hits.get(3).hole);
		Assert.assertEquals(Hole.HOLE_60, processorStub.hits.get(4).hole);
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseEmptyLines() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"\n" +
				"1 10\n" +
				"3 60");
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseWrongHoleScore() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"1 55\n" +
				"3 60");
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseMissingHoleScore() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"1\n" +
				"3 60");
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseOutOfRangeMaxLaneNumber() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"8 10\n" +
				"3 60");
	}
	
	@Test(expected = RaceInputException.class)
	public void cannotParseOutOfRangeMinLaneNumber() throws RaceInputException {
		RaceInputProcessorStub processorStub = new RaceInputProcessorStub();
		
		RaceInputParser parser = new RaceInputParser(processorStub);
		parser.parse("test1, test2, test3, test4\n" +
				"0 10\n" +
				"3 60");
	}
	
}
