package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@Ignore
public class HorseRacingIntegrationTest {
    private static final String SAMPLE_INPUT = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
            "1 60\n"+
            "3 5\n"+
            "1 60\n"+
            "4 5\n"+
            "4 10\n"+
            "2 5\n"+
            "5 10\n"+
            "1 60\n"+
            "3 20\n"+
            "7 10\n"+
            "1 40\n"+
            "2 60";
    private static final String SAMPLE_OUTPUT = "Position, Lane, Horse name\n"+
            "1, 1, Star\n"+
            "2, 3, Cheyenne\n"+
            "3, 4, Misty\n"+
            "4, 5, Spirit\n"+
            "5, 2, Dakota";



    private InputValidator validator;
    private InputProcessor inputProcessor;
    private OutputWriter outputWriter;
    private HorseRacingService horseRacingService;
    private HorseRacingController horseRacingController;
    private ModelMap modelMap;

    @Before
    public void setUp() throws Exception {
        validator = new InputValidator();
        inputProcessor = new InputProcessor(validator);
        outputWriter = new OutputWriter();
        horseRacingService = new HorseRacingService(inputProcessor, outputWriter);
        horseRacingController = new HorseRacingController(horseRacingService);
        modelMap = new ModelMap();
    }

    @Test
    public void horseRacingIntegrationTest() throws Exception {
        String expectedViewName = horseRacingController.exercise(SAMPLE_INPUT, modelMap);

        assertThat((String) modelMap.get("output"), equalTo(SAMPLE_OUTPUT));
        assertThat(expectedViewName, equalTo("excercise"));
    }
}
