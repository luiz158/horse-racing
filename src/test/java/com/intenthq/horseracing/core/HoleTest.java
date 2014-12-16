package com.intenthq.horseracing.core;

import junit.framework.Assert;

import org.junit.Test;

public class HoleTest {
	
	@Test
	public void canConvertStringToHole() {
		Assert.assertEquals(Hole.HOLE_5, Hole.fromString("5"));
		Assert.assertEquals(Hole.HOLE_10, Hole.fromString("10"));
		Assert.assertEquals(Hole.HOLE_20, Hole.fromString("20"));
		Assert.assertEquals(Hole.HOLE_40, Hole.fromString("40"));
		Assert.assertEquals(Hole.HOLE_60, Hole.fromString("60"));
	}
	
	@Test
	public void cannotConvertUnknownStringToHole() {
		Assert.assertNull(Hole.fromString("UNKNOWN"));
		Assert.assertNull(Hole.fromString("99"));
	}
	
	@Test
	public void canGetValueFromHole() {
		Assert.assertEquals(5, Hole.HOLE_5.getValue());
		Assert.assertEquals(10, Hole.HOLE_10.getValue());
		Assert.assertEquals(20, Hole.HOLE_20.getValue());
		Assert.assertEquals(40, Hole.HOLE_40.getValue());
		Assert.assertEquals(60, Hole.HOLE_60.getValue());
	}

}
