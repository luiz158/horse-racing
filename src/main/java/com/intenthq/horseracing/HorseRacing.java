package com.intenthq.horseracing;

import com.intenthq.horseracing.engine.RacingEngine;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.parser.InputParser;
import com.intenthq.horseracing.parser.OutputFormatter;

import java.util.Map;

public class HorseRacing {
    public static final int FURLONG = 220;
    private final InputParser inputParser;
    private final OutputFormatter outputFormatter;

    public HorseRacing(InputParser inputParser, OutputFormatter outputFormatter) {
        this.inputParser = inputParser;
        this.outputFormatter = outputFormatter;
    }

    public String startRaceWith(String raceInput) {
        InputParser parser = inputParser.parse(raceInput);
        Map<Integer, RaceHorse> raceHorses = parser.raceHorses();

        RacingEngine racingEngine = new RacingEngine(FURLONG);
        racingEngine.setupCourse(raceHorses);
        racingEngine.runPlays(parser.plays());

        return outputFormatter.format(raceHorses);
    }
}
