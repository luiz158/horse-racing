package com.intenthq.horseracing;

import com.intenthq.horseracing.exception.BallTossInvalidException;
import com.intenthq.horseracing.exception.NoValidHorsesException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Component
public class InputValidator {

    public static final int MIN_NO_HORSES = 1;
    public static final int MAX_NO_HORSES = 7;
    public static final String BALL_TOSS_REGEX = "^\\s*([%s-%s])\\s+(5|10|20|40|60)\\s*$";
    public static final int LANE_INDEX = 1;
    private static final String HORSE_DELIMITER = ",";

    public InputValidator() {
    }

    public void validateHorses(String horseString) throws NoValidHorsesException {

        if (StringUtils.isBlank(horseString)) {
            throw new NoValidHorsesException("noValidHorses", format("No Horses in race"));
        }

        String[] horseNames = horseString.split(HORSE_DELIMITER);

        if (horseNames.length < MIN_NO_HORSES) {
            throw new NoValidHorsesException("noValidHorses", format("No Horses in race"));
        }

        if (horseNames.length > MAX_NO_HORSES) {
            throw new NoValidHorsesException("horseLimitExceeded", format("Only %s Horses are allowed to run", MAX_NO_HORSES));
        }

    }

    public void validateBallToss(String ballToss, int numberOfHorses) throws BallTossInvalidException {

        Pattern pattern = Pattern.compile(format(BALL_TOSS_REGEX, MIN_NO_HORSES, MAX_NO_HORSES));
        Matcher matcher = pattern.matcher(ballToss);

        if (!matcher.matches()) {
            throw new BallTossInvalidException("ballTossInvalid", format("invalid ball toss: %s", ballToss));
        }

        if (Integer.parseInt(matcher.group(LANE_INDEX)) > numberOfHorses) {
            throw new BallTossInvalidException("ballTossInvalid", format("No Horse in lane: %s", matcher.group(LANE_INDEX)));
        }
    }
}
