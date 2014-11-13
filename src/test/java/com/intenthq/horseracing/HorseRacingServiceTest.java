package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HorseRacingServiceTest {

    private HorseRacingService horseRacingService;
    @Mock
    private InputProcessor inputProcessor;
    @Mock
    private OutputWriter outputWriter;

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


    @Before
    public void setUp() throws Exception {
        horseRacingService = new HorseRacingService(inputProcessor, outputWriter);
    }

    @Test
    public void processRaceShouldReturnFormattedOutputDataForOneHorse(){
        final Map<Integer, Horse> horses = buildTestHorsesMap();

        when(inputProcessor.parse(SAMPLE_INPUT)).thenReturn(horses);
        when(outputWriter.print(horses)).thenReturn(SAMPLE_OUTPUT);

        final String actual = horseRacingService.processRace(SAMPLE_INPUT);

        verify(inputProcessor).parse(SAMPLE_INPUT);
        verify(outputWriter).print(horses);

        assertThat(actual, equalTo(SAMPLE_OUTPUT));

    }

    private Map<Integer, Horse> buildTestHorsesMap() {
        final Map<Integer, Horse> horses = newHashMap();

        horses.put(new Integer(1), new Horse("Star"));
        horses.put(new Integer(2), new Horse("Dakota"));
        horses.put(new Integer(3), new Horse("Cheyenne"));
        horses.put(new Integer(4), new Horse("Misty"));
        horses.put(new Integer(5), new Horse("Spirit"));

        return horses;
    }
}