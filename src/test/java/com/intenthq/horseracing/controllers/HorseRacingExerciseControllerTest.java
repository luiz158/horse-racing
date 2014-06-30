package com.intenthq.horseracing.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.io.FilterInputStream;
import java.io.FilterReader;
import java.io.LineNumberReader;

import com.intenthq.horseracing.factory.RacerFactory;
import com.intenthq.horseracing.model.Racer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HorseRacingExerciseControllerTest
{

	private ModelMap model = new ModelMap();

    @Spy
    private Race race;
    
    @InjectMocks
	private HorseRacingExerciseController horseRacingExerciseController;


	private static final String SOME_INPUT = "input,";

	private static final String EMPTY_OUTPUT = "";
	private static final String SAMPLE_INPUT_NORMAL_FINISHED = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
											   "1 60\n"+
                                               "3 5\n"+
                                               "4 260\n";	
                                               

	private static final String SAMPLE_OUTPUT_NORMAL_FINISHED = "Position, Lane, Horse name\n"+
											    "1, 4, Misty\n"+
											    "2, 1, Star\n"+
											    "3, 3, Cheyenne\n"+
											    "4, 2, Dakota\n"+
											    "5, 5, Spirit\n"+
											    "\nThe race is finished!";

	private static final String SAMPLE_INPUT_NORMAL_NOT_FINISHED = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
                                               "1 60\n"+
                                               "3 5\n";

	private static final String SAMPLE_OUTPUT_NORMAL_NOT_FINISHED = "Position, Lane, Horse name\n"+
											    "1, 1, Star\n"+
											    "2, 3, Cheyenne\n"+
											    "3, 2, Dakota\n"+
											    "4, 4, Misty\n"+
											    "5, 5, Spirit\n";

	private static final String SAMPLE_OUTPUT_NOT_VALID = "The input is not valid, probably one of the tracks does not exist!";

	private static final String SAMPLE_INPUT_NOT_VALID1 = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
                                               "1 60\n"+
                                               "3 5\n"+
                                               "7 60\n";

    private static final String SAMPLE_INPUT_NOT_VALID2 = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
                                               "1 60\n"+
                                               "3 5\n"+
                                               "7 60\n";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void exerciseShouldReturnTheExerciseView() throws Exception {
		final String actual = horseRacingExerciseController.exercise(SOME_INPUT, model);
		assertThat(actual, is("exercise"));
	}

	@Test
	public void exerciseWithNoInputShouldNotReturnOutput() throws Exception {
		horseRacingExerciseController.exercise(null, model);
		assertThat(model.containsAttribute(HorseRacingExerciseControllerTest.EMPTY_OUTPUT), is(false));
	}

	@Test
	public void exerciseWithInputShouldReturnSomeOutput() throws Exception {
		horseRacingExerciseController.exercise(SAMPLE_INPUT_NORMAL_FINISHED, model);
		assertThat(model.containsAttribute(HorseRacingExerciseController.OUTPUT_ATT), is(true));
	}

	@Test
	public void exerciseWithInputShouldAddTheInputToTheModel() throws Exception {
		horseRacingExerciseController.exercise(SOME_INPUT, model);
		final String input =(String) model.get(HorseRacingExerciseController.INPUT_ATT);
		assertThat(input, is(SOME_INPUT));
	}

	@Test
	public void exerciseWithSampleInputShouldReturnFinished() throws Exception {
		race.initRace(5,220);
		horseRacingExerciseController.exercise(SAMPLE_INPUT_NORMAL_FINISHED, model);
		final String output = (String) model.get(HorseRacingExerciseController.OUTPUT_ATT);
		assertThat(output, is(SAMPLE_OUTPUT_NORMAL_FINISHED));
	}

	@Test
	public void exerciseWithSampleInputShouldReturNonFinished() throws Exception {
		race.initRace(5,220);
		horseRacingExerciseController.exercise(SAMPLE_INPUT_NORMAL_NOT_FINISHED, model);
		final String output = (String) model.get(HorseRacingExerciseController.OUTPUT_ATT);
		assertThat(output, is(SAMPLE_OUTPUT_NORMAL_NOT_FINISHED));
	}

	@Test
	public void exerciseWithSampleInputShouldReturNotValid1() throws Exception {
		race.initRace(5,220);
		horseRacingExerciseController.exercise(SAMPLE_INPUT_NOT_VALID1, model);
		final String output = (String) model.get(HorseRacingExerciseController.OUTPUT_ATT);
		assertThat(output, is(SAMPLE_OUTPUT_NOT_VALID));
	}

	@Test
	public void exerciseWithSampleInputShouldReturNotValid2() throws Exception {
		race.initRace(5,220);
		horseRacingExerciseController.exercise(SAMPLE_INPUT_NOT_VALID2, model);
		final String output = (String) model.get(HorseRacingExerciseController.OUTPUT_ATT);
		assertThat(output, is(SAMPLE_OUTPUT_NOT_VALID));
	}
}
