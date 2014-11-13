package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.intenthq.horseracing.TestHelper.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HorseRacingServiceTest {

    public static final Horse STAR = new Horse("Star", 220);
    public static final Horse DAKOTA = new Horse("Dakota", 10);
    public static final Horse CHEYENNE = new Horse("Cheyenne", 125);
    public static final Horse MISTY = new Horse("Misty", 50);
    public static final Horse SPIRIT = new Horse("Spirit", 30);

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
    public void processRaceShouldReturnOutputFromOutputWriter(){
        final Map<Integer, Horse> horses = buildTestHorsesMap();

        when(inputProcessor.parse(SAMPLE_INPUT)).thenReturn(horses);
        when(outputWriter.print(getSortedHorses())).thenReturn(SAMPLE_OUTPUT);

        final String actual = horseRacingService.processRace(SAMPLE_INPUT);

        assertThat(actual, equalTo(SAMPLE_OUTPUT));
    }

    @Test
    public void processRaceShouldPassHorsesFromGreatestDistanceDescToTheOutputWriter() throws Exception {
        final Map<Integer, Horse> horses = buildTestHorsesMap();
        List<Map.Entry<Integer,Horse>> sortedHorseList = getSortedHorses();

        when(inputProcessor.parse(SAMPLE_INPUT)).thenReturn(horses);

        horseRacingService.processRace(SAMPLE_INPUT);

        verify(outputWriter).print(sortedHorseList);

    }

    private List<Map.Entry<Integer,Horse>> getSortedHorses() {
        return newArrayList(
                createHorseEntry(1, STAR),
                createHorseEntry(3, CHEYENNE),
                createHorseEntry(4, MISTY),
                createHorseEntry(5, SPIRIT),
                createHorseEntry(2, DAKOTA)
        );
    }


    private Map<Integer, Horse> buildTestHorsesMap() {
        final Map<Integer, Horse> horses = newHashMap();

        horses.put(new Integer(1), STAR);
        horses.put(new Integer(2), DAKOTA);
        horses.put(new Integer(3), CHEYENNE);
        horses.put(new Integer(4), MISTY);
        horses.put(new Integer(5), SPIRIT);

        return horses;
    }
}