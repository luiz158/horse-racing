package com.intenthq.horseracing.parser;

import com.google.common.collect.ImmutableMap;
import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.exceptions.InputParsingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InputParserTest {

    private InputParser inputParser;

    @Before
    public void setUp() throws Exception {
        inputParser = new InputParser();
    }

    @Test
    public void shouldParseFirstLineOfInputToAListOfHorses() throws Exception {
        String input = "Horse1, Dakota, Little Pony\n" +
                "1 10\n" +
                "1 60\n" +
                "4 5\n" +
                "4 10";
        Map<Integer, RaceHorse> expectedHorseList = ImmutableMap.<Integer, RaceHorse>builder()
                .put(1, new RaceHorse(1, "Horse1"))
                .put(2, new RaceHorse(2, "Dakota"))
                .put(3, new RaceHorse(3, "Little Pony")).build();

        assertThat(inputParser.parse(input).raceHorses(), is(expectedHorseList));
    }

    @Test
    public void shouldSubsequentLinesOfInputToAListPlays() throws Exception {
        String input = "Horse1\n" +
                "1 10\n" +
                "1 60\n" +
                "4 5\n" +
                "4 10";

        List<Play> expectedPlays = newArrayList(new Play(1, 10), new Play(1, 60), new Play(4, 5), new Play(4, 10));
        assertThat(inputParser.parse(input).plays(), is(expectedPlays));
    }

    @Test
    public void shouldThrowExceptionWhenInvalidPlayOnInput() throws Exception {
        try {
            String input = "Horse1\n" +
                    "1 10\n" +
                    "a 60\n" +
                    "4 5\n" +
                    "4 10";
            inputParser.parse(input);
            Assert.fail(String.format("Expected [%s]", InputParsingException.class.getSimpleName()));
        } catch (InputParsingException expected) {
            assertThat(expected.getMessage(), is("Invalid play on input:[a 60]"));
        }
    }


    @Test
    public void stuff() throws Exception{
        String SAMPLE_INPUT = "Star, Dakota, Cheyenne, Misty, Spirit\n"+
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

        System.out.println("inputParser = " + inputParser.parse(SAMPLE_INPUT).plays());
    }
}
