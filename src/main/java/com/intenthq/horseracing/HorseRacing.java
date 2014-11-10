package com.intenthq.horseracing;

import com.intenthq.horseracing.engine.RacingEngine;
import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.parser.InputParser;
import com.intenthq.horseracing.parser.OutputFormatter;

import java.util.List;
import java.util.Map;

public class HorseRacing {

    private final InputParser inputParser;
    private final OutputFormatter outputFormatter;

    public HorseRacing(InputParser inputParser, OutputFormatter outputFormatter) {
        this.inputParser = inputParser;
        this.outputFormatter = outputFormatter;
    }

    public String startRaceWith(String raceInput) {
        InputParser parser = inputParser.parse(raceInput);
        Map<Integer, RaceHorse> raceHorses = parser.raceHorses();

        RacingEngine racingEngine = new RacingEngine(raceHorses, 220);
        List<Play> plays = parser.plays();
        for (int i = 0; i < plays.size() && !racingEngine.isEnded(); i++) {
            racingEngine.runPlay(plays.get(i));
        }

        return outputFormatter.format(raceHorses);
    }
}
