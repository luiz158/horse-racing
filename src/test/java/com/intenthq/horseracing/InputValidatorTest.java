package com.intenthq.horseracing;

import com.intenthq.horseracing.exception.BallTossInvalidException;
import com.intenthq.horseracing.exception.NoValidHorsesException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        inputValidator = new InputValidator();
    }

    @Test
    public void validateHorsesShouldThrowExceptionIfEmptyString() {
        NoValidHorsesException actual = null;
        try {
            inputValidator.validateHorses("");
        } catch (NoValidHorsesException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("No Horses in race"));
    }

    @Test
    public void validateHorsesShouldThrowExceptionIfJustCommas() {
        NoValidHorsesException actual = null;
        try {
            inputValidator.validateHorses(",,,,");
        } catch (NoValidHorsesException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("No Horses in race"));
    }

    @Test
    public void validateHorsesShouldThrowExceptionIfMoreThanSevenHorses() {
        NoValidHorsesException actual = null;

        try {
            inputValidator.validateHorses("1, 2, 3, 4, 5, 6, 7, 8");
        } catch (NoValidHorsesException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("Only 7 Horses are allowed to run"));
    }

    @Test
    public void validateHorsesShouldNotThrowExceptionIfOneHorse() {
        try {
            inputValidator.validateHorses("1\n");
        } catch (NoValidHorsesException e) {
            fail();
        }
    }
    @Test
    public void validateHorsesShouldNotThrowExceptionIfValidHorseLine() {
        try {
            inputValidator.validateHorses("Star, 1&2, Sailor, Don_dario, **, <>, Tap-tap\n");
        } catch (NoValidHorsesException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void validateBallTossShouldNotThrowExceptionIfValidLine()  {
        try {
            inputValidator.validateBallToss("1 60", 1);
        } catch (BallTossInvalidException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    public void validateBallTossShouldThrowExceptionIfInValidLane()  {
        BallTossInvalidException actual = null;
        try {
            inputValidator.validateBallToss("a 60", 1);
        } catch (BallTossInvalidException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("invalid ball toss: a 60"));
    }

    @Test
    public void validateBallTossShouldThrowExceptionIfInValidThrowValue()  {
        BallTossInvalidException actual = null;
        try {
            inputValidator.validateBallToss("1 230", 1);
        } catch (BallTossInvalidException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("invalid ball toss: 1 230"));
    }

    @Test
    public void validateBallTossShouldThrowExceptionIfLaneGreaterThanNumberOfHorses()  {
        BallTossInvalidException actual = null;
        try {
            inputValidator.validateBallToss("2 60", 1);
        } catch (BallTossInvalidException e) {
            actual = e;
        }

        assertThat(actual.getMessage(), equalTo("No Horse in lane: 2"));
    }

    @Test
    public void validateBallTossShouldTIgnoreWhiteSpaceBeforeLane()  {
        try {
            inputValidator.validateBallToss("   1 60", 1);
        } catch (BallTossInvalidException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    public void validateBallTossShouldTIgnoreWhiteSpaceAfterThrowValue()  {
        try {
            inputValidator.validateBallToss("1 60  ", 1);
        } catch (BallTossInvalidException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    public void validateBallTossShouldTHandleMoreThanOneWhiteSpaceBetweenValues()  {
        try {
            inputValidator.validateBallToss("1   60", 1);
        } catch (BallTossInvalidException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}
