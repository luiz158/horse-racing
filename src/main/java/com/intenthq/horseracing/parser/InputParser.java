package com.intenthq.horseracing.parser;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.exceptions.InputParsingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.valueOf;

/**
* Created by antmyth on 10/11/14.
*/
public class InputParser {
    private static final Splitter SPACE_SPLITTER = Splitter.on(' ').trimResults();
    private Map<Integer,RaceHorse> raceHorses;
    private List<Play> plays;

    public InputParser parse(String input) {
        List<String> inputAsList = Splitter.on('\n').splitToList(input);
        raceHorses = parseRaceHorses(inputAsList.get(0));
        plays = parsePlays(inputAsList.subList(1, inputAsList.size()));
        return this;
    }

    private Map<Integer,RaceHorse> parseRaceHorses(String horsesNamesToParse) {
        List<String> horsesNames = Splitter.on(',').trimResults().splitToList(horsesNamesToParse);
        Map<Integer,RaceHorse> raceHorses = new HashMap<Integer,RaceHorse>();
        for (int i = 0; i < horsesNames.size(); i++) {
            raceHorses.put(i + 1, new RaceHorse(i + 1, horsesNames.get(i)));
        }
        return raceHorses;
    }

    private List<Play> parsePlays(List<String> playsToParse) {
        List<Play> plays = newArrayList();
        for (String playAsString : playsToParse) {
            try {
                plays.add(parsePlayFrom(playAsString));
            } catch (NumberFormatException e) {
                throw new InputParsingException(String.format("Invalid play on input:[%s]",playAsString),e);
            }
        }
        return plays;
    }

    private Play parsePlayFrom(String playAsString) {
        Integer[] playAsIntegers = transform(SPACE_SPLITTER.splitToList(playAsString), new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return valueOf(input);
            }
        }).toArray(new Integer[2]);

        return new Play(playAsIntegers[0], playAsIntegers[1]);
    }

    public Map<Integer, RaceHorse> raceHorses() {
        return raceHorses;
    }

    public List<Play> plays() {
        return plays;
    }
}
