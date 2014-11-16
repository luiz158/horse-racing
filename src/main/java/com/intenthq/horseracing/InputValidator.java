package com.intenthq.horseracing;

import org.apache.commons.lang3.StringUtils;

public class InputValidator {

    public static final int MIN_NO_HORSES = 1;
    public static final int MAX_NO_HORSES = 7;

    public void validateHorses(String horseString) throws NoValidHorsesException {

        if (StringUtils.isBlank(horseString)) {
            throw new NoValidHorsesException("noValidHorses", String.format("No Horses in race"));
        }

        String[] horseNames = horseString.split(",");

        if (horseNames.length < MIN_NO_HORSES) {
            throw new NoValidHorsesException("noValidHorses", String.format("No Horses in race"));
        }

        if (horseNames.length > MAX_NO_HORSES) {
            throw new NoValidHorsesException("horseLimitExceeded", String.format("Only %s Horses are allowed to run", MAX_NO_HORSES));
        }

    }

    public void validateBallToss(String ballToss) throws BallTossInvalidException {
        // test if both numbers are integers
        // positive integers
        // test that the horse exists in the map.
        // 2 numbers

        throw new BallTossInvalidException("ballTossInvalid", String.format("Invalid Ball toss [%s]", ballToss));
    }
}
