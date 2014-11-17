package com.intenthq.horseracing;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutputWriter {

    private static final String HEADING = "Position, Lane, Horse name\n";
    public static final int FIRST = 1;
    public static final String COMMA_DELIMITER = ", ";
    public static final String NEW_LINE_DELIMITER = "\n";

    public OutputWriter() {
    }

    public String print(final List<Horse> sortedHorseList) {
        final StringBuilder stringBuilder = new StringBuilder(HEADING);

        int position = 0;
        int previousHorseDistanceCovered = 0;
        for (Horse horse : sortedHorseList) {
            position++;
            if (horseTiedWithPreviousHorse(position, previousHorseDistanceCovered, horse)) {
                printLaneEntry(stringBuilder, position - 1, horse);
            } else {
                printLaneEntry(stringBuilder, position, horse);
            }

            previousHorseDistanceCovered = horse.getYardsCovered();

        }

        return stringBuilder.toString().trim();

    }

    private boolean horseTiedWithPreviousHorse(final int position,
                                               final int previousHorseDistanceCovered,
                                               final Horse horse) {

        return previousHorseDistanceCovered == horse.getYardsCovered() && position != FIRST;
    }

    private void printLaneEntry(final StringBuilder sb, final int position, final Horse horse) {
        sb.append(String.valueOf(position)).append(COMMA_DELIMITER);
        sb.append(horse.getLane()).append(COMMA_DELIMITER);
        sb.append(horse.getHorseName()).append(NEW_LINE_DELIMITER);
    }
}
