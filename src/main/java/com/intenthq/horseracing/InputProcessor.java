package com.intenthq.horseracing;

import com.google.common.base.Splitter;
import com.intenthq.horseracing.exception.BallTossInvalidException;
import com.intenthq.horseracing.exception.NoValidHorsesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

import static com.google.common.collect.Maps.newHashMap;

@Component
public class InputProcessor {

    public static final int BALL_TOSS_DISTANCE_INDEX = 1;
    public static final int BALL_TOSS_LANE_INDEX = 0;
    private final InputValidator validator;

    @Autowired
    public InputProcessor(InputValidator validator) {
        this.validator = validator;
    }

    public Map<Integer, Horse> process(final String raceInput) throws NoValidHorsesException {
        Map<Integer, Horse> horseMap = newHashMap();
        boolean raceFinished = false;

        Scanner scan = new Scanner(raceInput);

        if (scan.hasNextLine()) {
            String horseLanesString = scan.nextLine();
            validator.validateHorses(horseLanesString);

            Iterable<String> horseNames = splitHorsesOnCommas(horseLanesString);
            int lane = 1;
            for (String horseName : horseNames) {
                addHorseToMap(horseMap, horseName, lane);
                lane++;
            }
        }

        while (scan.hasNextLine() && !raceFinished) {
            try {
                String ballTossString = scan.nextLine();
                validator.validateBallToss(ballTossString, horseMap.size());
                String[] ballToss = ballTossString.split("\\s");

                Horse horse = horseMap.get(Integer.valueOf(ballToss[BALL_TOSS_LANE_INDEX]));
                horse.addYards(Integer.parseInt(ballToss[BALL_TOSS_DISTANCE_INDEX]));

                if(horse.isFinished()) {
                    raceFinished = true;
                }

            } catch (BallTossInvalidException e) {
                // log an error message
                // but continue processing
            }
        }

        scan.close();
        return horseMap;
    }

    private Iterable<String> splitHorsesOnCommas(String horseLanesString) {
        return Splitter.on(",").trimResults().split(horseLanesString);
    }

    private void addHorseToMap(Map<Integer, Horse> horseMap, String horseName, int lane) {
        Integer horseKey = new Integer(lane);
        Horse horse = new Horse(lane, horseName);
        horseMap.put(horseKey, horse);
    }
}
