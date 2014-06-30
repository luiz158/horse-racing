package com.intenthq.horseracing.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.io.FilterInputStream;
import java.io.FilterReader;
import java.io.LineNumberReader;

import com.intenthq.horseracing.model.Racer;
import com.intenthq.horseracing.factory.RacerFactory;
import com.intenthq.horseracing.NonExistingTrackException;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RaceTest
{
	private Race race;

	private static final String SAMPLE_OUTPUT = "Position, Lane, Horse name\n"+
											    "1, 1, racer1\n"+
											    "2, 2, racer2\n";

	private static final String SAMPLE_OUTPUT2 = "Position, Lane, Horse name\n"+
											    "1, 2, racer2\n"+
											    "2, 1, racer1\n";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected=NonExistingTrackException.class)
	public void addNewRacerShouldThrowExceptionIfRaceIsNotInitialized() throws Exception {
		race = new Race();
		race.addNewRacer(null,0);
	}

	@Test
	public void addNewRacerShouldResultANonEmptyList() throws Exception  {
		race = new Race();
		race.initRace(2,100);
		race.addNewRacer(new Racer(),0);
		assertThat(race.hasRacers(), is(true));
	}

	@Test(expected=NonExistingTrackException.class)
	public void addNewRacerShouldThrowExceptionIfThereIsNoSpace() throws Exception {
		race = new Race();
		race.initRace(2,100);
		race.addNewRacer(new Racer(),0);
		race.addNewRacer(new Racer(),1);
		race.addNewRacer(new Racer(),2);
	}

	@Test(expected=NonExistingTrackException.class)
	public void getRaceStateShouldThrowExceptionIfRaceIsNotInitialized() throws Exception  {
		race = new Race();
		race.getRaceState();
	}

	@Test
	public void getRaceStateShouldReturnState() throws Exception {
		race = new Race();
		race.initRace(2,100);
		race.addNewRacer(RacerFactory.createNewHorseRacer("racer1"),0);
		race.addNewRacer(RacerFactory.createNewHorseRacer("racer2"),1);

		assertThat(race.getRaceState(), is(SAMPLE_OUTPUT));
	}

	@Test
	public void getRaceStateShouldReturnChangedState() throws Exception  {
		race = new Race();
		race.initRace(2,100);
		race.addNewRacer(RacerFactory.createNewHorseRacer("racer1"),0);
		race.addNewRacer(RacerFactory.createNewHorseRacer("racer2"),1);

		String[] updates = new String[1];
		updates[0]="2 10";

		race.updateRace(updates);
		assertThat(race.getRaceState(), is(SAMPLE_OUTPUT2));
	}


}
