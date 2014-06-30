package com.intenthq.horseracing.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HorseRacingControllerTest
{
	private ModelMap model = new ModelMap();

	private HorseRacingController horseRacingController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		horseRacingController = new HorseRacingController();
	}

	@Test
	public void horseRacingShouldReturnTheViewContainingTheWordingOfTheExercise() throws Exception {
		final String actual = horseRacingController.horseRacing(model);
		assertThat(actual, is("horse-racing"));
	}
}