package com.intenthq.horseracing;

public class InputValidator {
    public void validateHorses(String horseString) throws NoValidHorsesException {
        // test there are less than 7 horses
        // test that there will be at least one horse
        throw new NoValidHorsesException("noValidHorses", String.format("There are no valid horses in the race"));
    }

    public void validateBallToss(String ballToss) throws BallTossInvalidException {
        // test if both numbers are integers
        // positive integers
        // test that the horse exists in the map.

        throw new BallTossInvalidException("ballTossInvalid", String.format("Invalid Ball toss [%s]", ballToss));
    }
}
