package com.intenthq.horseracing;

import java.util.List;
import java.util.Map;

public class OutputWriter {

    private static final String HEADING = "Position, Lane, Horse name\n";

    public String print(final List<Map.Entry<Integer, Horse>> sortedHorseList) {
        final StringBuilder sb = new StringBuilder(HEADING);

        int position = 0;
        int previousHorseDistanceCovered = 0;
        for (Map.Entry<Integer, Horse> laneEntry : sortedHorseList) {
            position++;
            if (horseTiedWithPreviousHorse(position, previousHorseDistanceCovered, laneEntry)) {
                printLaneEntry(sb, position - 1, laneEntry);
            } else {
                printLaneEntry(sb, position, laneEntry);
            }

            previousHorseDistanceCovered = laneEntry.getValue().getYardsCovered();

        }

        removeTrailingNewLine(sb);
        return sb.toString();

    }

    private boolean horseTiedWithPreviousHorse(final int position,
                                               final int previousHorseDistanceCovered,
                                               final Map.Entry<Integer, Horse> laneEntry) {

        return previousHorseDistanceCovered == laneEntry.getValue().getYardsCovered() && position != 1;
    }

    private void printLaneEntry(final StringBuilder sb, final int position, final Map.Entry<Integer, Horse> laneEntry) {
        sb.append(String.valueOf(position)).append(", ");
        sb.append(laneEntry.getKey()).append(", ");
        sb.append(laneEntry.getValue().getHorseName()).append("\n");
    }

    private void removeTrailingNewLine(final StringBuilder sb) {
        sb.setLength(sb.length() - 1);
    }
}
