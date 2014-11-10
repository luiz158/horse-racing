package com.intenthq.horseracing.parser;

import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.exceptions.InputParsingException;
import com.intenthq.horseracing.parser.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.valueOf;
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
        List<RaceHorse> expectedHorseList = newArrayList(
                new RaceHorse(1, "Horse1"),
                new RaceHorse(2, "Dakota"),
                new RaceHorse(3, "Little Pony"));

        assertThat(inputParser.parse(input).raceHorses(), CoreMatchers.is(expectedHorseList));
    }

    @Test
    public void shouldSubsequentLinesOfInputToAListPlays() throws Exception {
        String input = "Horse1\n" +
                "1 10\n" +
                "1 60\n" +
                "4 5\n" +
                "4 10";

        List<Play> expectedPlays = newArrayList(new Play(1, 10),new Play(1, 60),new Play(4, 5),new Play(4, 10));
        assertThat(inputParser.parse(input).plays(), CoreMatchers.is(expectedPlays));
    }

    @Test
    public void shouldThrowExceptionWhenInvalidPlayOnInput() throws Exception{
    	try{
            String input = "Horse1\n" +
                    "1 10\n" +
                    "a 60\n" +
                    "4 5\n" +
                    "4 10";
            inputParser.parse(input);
    		Assert.fail(String.format("Expected [%s]", InputParsingException.class.getSimpleName()));
    	} catch (InputParsingException expected){
            assertThat(expected.getMessage(), CoreMatchers.is("Invalid play on input:[a 60]"));
    	}
    }

}
