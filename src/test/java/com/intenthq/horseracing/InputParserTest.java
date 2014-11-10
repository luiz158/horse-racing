package com.intenthq.horseracing;

import com.google.common.base.Splitter;
import com.intenthq.horseracing.entities.RaceHorse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InputParserTest {

    @Test
    public void shouldParseFirstLineOfInputToAListOfHorses() throws Exception {
        String input = "Horse1, Dakota, Little Pony\n" +
                "1 10";
        List<RaceHorse> expectedHorseList = newArrayList(
                new RaceHorse(1, "Horse1"),
                new RaceHorse(2, "Dakota"),
                new RaceHorse(3, "Little Pony"));

        assertThat(new InputParser().parse(input).raceHorses(), is(expectedHorseList));
    }

    public static class InputParser {

        private List<RaceHorse> raceHorses;

        public InputParser parse(String input) {
            List<String> inputAsList = Splitter.on('\n').splitToList(input);
            raceHorses = parseRaceHorses(inputAsList.get(0));
            return this;
        }

        private List<RaceHorse> parseRaceHorses(String horsesNamesToParse) {
            List<String> horsesNames = Splitter.on(',').trimResults().splitToList(horsesNamesToParse);
            ArrayList<RaceHorse> raceHorses = new ArrayList<RaceHorse>();
            for (int i = 0; i < horsesNames.size(); i++) {
                raceHorses.add(new RaceHorse(i+1,horsesNames.get(i)));
            }

            return raceHorses;
        }

        public List<RaceHorse> raceHorses() {
            return raceHorses;
        }
    }

}
