package com.intenthq.horseracing;

import java.util.List;

public class OutputWriter {

    private static final String HEADING = "Position, Lane, Horse name\n";

    public String print(final List<Horse> sortedHorseList) {
        final StringBuilder sb = new StringBuilder(HEADING);

        int position = 0;
        int previousHorseDistanceCovered = 0;
        for (Horse horse : sortedHorseList) {
            position++;
            if (horseTiedWithPreviousHorse(position, previousHorseDistanceCovered, horse)) {
                printLaneEntry(sb, position - 1, horse);
            } else {
                printLaneEntry(sb, position, horse);
            }

            previousHorseDistanceCovered = horse.getYardsCovered();

        }

        removeTrailingNewLine(sb);
        return sb.toString();

    }

    private boolean horseTiedWithPreviousHorse(final int position,
                                               final int previousHorseDistanceCovered,
                                               final Horse horse) {

        return previousHorseDistanceCovered == horse.getYardsCovered() && position != 1;
    }

    private void printLaneEntry(final StringBuilder sb, final int position, final Horse horse) {
        sb.append(String.valueOf(position)).append(", ");
        sb.append(horse.getLane()).append(", ");
        sb.append(horse.getHorseName()).append("\n");
    }

    private void removeTrailingNewLine(final StringBuilder sb) {
        sb.setLength(sb.length() - 1);
    }
}
