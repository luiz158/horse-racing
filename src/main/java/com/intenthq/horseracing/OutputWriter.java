package com.intenthq.horseracing;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputWriter {

    private static final String HEADING = "Position, Lane, Horse name\n";

    public String print(final Map<Integer, Horse> horses) {
        final StringBuilder sb = new StringBuilder(HEADING);

        final List<Map.Entry<Integer, Horse>> sortedLaneList = sorthorses(horses);

        int position = 0;
        int previousHorseDistanceCovered = 0;
        for (Map.Entry<Integer, Horse> laneEntry : sortedLaneList) {
            position++;
            if(horseTiedWithPreviousHorse(position, previousHorseDistanceCovered, laneEntry)) {
                printLaneEntry(sb, position - 1, laneEntry);
            } else {
                printLaneEntry(sb, position, laneEntry);
            }

            previousHorseDistanceCovered = laneEntry.getValue().getYardsCovered();

        }

        removeTrailingNewLine(sb);

        return sb.toString();

    }

    private boolean horseTiedWithPreviousHorse(final int position, final int previousHorseDistanceCovered, final Map.Entry<Integer, Horse> laneEntry) {
        return previousHorseDistanceCovered == laneEntry.getValue().getYardsCovered() && position != 1;
    }

    private void printLaneEntry(final StringBuilder sb, final int position, final Map.Entry<Integer, Horse> laneEntry) {
        sb.append(String.valueOf(position)).append(", ");
        sb.append(laneEntry.getKey()).append(", ");
        sb.append(laneEntry.getValue().getHorseName()).append("\n");
    }

    private List<Map.Entry<Integer, Horse>> sorthorses(final Map<Integer, Horse> horses) {
        final List<Map.Entry<Integer, Horse>> laneList = Lists.newArrayList(horses.entrySet());
        Collections.sort(laneList, byDistance());
        return laneList;
    }

    private Ordering<Map.Entry<Integer, Horse>> byDistance() {
        return new Ordering<Map.Entry<Integer, Horse>>() {
            @Override
            public int compare(final Map.Entry<Integer, Horse> left, final Map.Entry<Integer, Horse> right) {
                return right.getValue().getYardsCovered() - left.getValue().getYardsCovered();
            }
        };
    }

    private void removeTrailingNewLine(final StringBuilder sb) {
        sb.setLength(sb.length() - 1);
    }
}
