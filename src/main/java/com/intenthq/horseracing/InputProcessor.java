package com.intenthq.horseracing;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

import static com.google.common.collect.Maps.newHashMap;

@Component
public class InputProcessor {

    public static final int BALL_TOSS_DISTANCE_INDEX = 1;
    public static final int BALL_TOSS_LANE_INDEX = 0;
    private final InputValidator validator;

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
            String[] horseNames = splitHorsesOnCommas(horseLanesString);

            for (int i = 0; i < horseNames.length; i++) {
                addHorseToMap(horseMap, horseNames[i], i);
            }
        }

        while (scan.hasNextLine() && !raceFinished) {
            try {
                String ballTossString = scan.nextLine();
                validator.validateBallToss(ballTossString);
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

        return horseMap;
    }

    private String[] splitHorsesOnCommas(String horseLanesString) {
        return horseLanesString.split("\\s*,\\s*");
    }

    private void addHorseToMap(Map<Integer, Horse> horseMap, String horseName, int i) {
        int lane = i + 1;
        Integer horseKey = new Integer(lane);
        Horse horse = new Horse(lane, horseName);
        horseMap.put(horseKey, horse);
    }
}
