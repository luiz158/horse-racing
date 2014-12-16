package com.intenthq.horseracing.core;

import junit.framework.Assert;

import org.junit.Test;

public class BasicRaceBuilderTest {
	
	@Test
	public void canBuild() {
		Horse h1 = new Horse("AA");
		Horse h2 = new Horse("BB");
		Horse h3 = new Horse("CC");
		
		BasicRaceBuilder builder = new BasicRaceBuilder();
		builder.addHorse(h1);
		builder.addHorse(h2);
		builder.addHorse(h3);
		
		Race race = builder.create();
		Assert.assertEquals(3, race.getResults().size());
		Assert.assertSame(1, race.getResults().get(0).getNumber());
		Assert.assertSame(2, race.getResults().get(1).getNumber());
		Assert.assertSame(3, race.getResults().get(2).getNumber());
	}

}
