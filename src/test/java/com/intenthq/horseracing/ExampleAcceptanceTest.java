package com.intenthq.horseracing;

import com.intenthq.horseracing.parser.InputParser;
import com.intenthq.horseracing.parser.OutputFormatter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExampleAcceptanceTest {
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

    @Test
    public void shouldReturnTheExpectedOutputForTheSampleInput(){
        HorseRacing horseRacing = new HorseRacing(new InputParser(),new OutputFormatter());

        assertThat(horseRacing.startRaceWith(SAMPLE_INPUT),is(SAMPLE_OUTPUT));
    }
}
