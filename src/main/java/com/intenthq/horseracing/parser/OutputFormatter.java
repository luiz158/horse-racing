package com.intenthq.horseracing.parser;

import com.intenthq.horseracing.entities.RaceHorse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;

/**
* Created by antmyth on 10/11/14.
*/
public class OutputFormatter {
    public static final String FIELD_SEPARATOR = ", ";
    public static final String LINE_SEPARATOR = "\n";
    public static final String OUTPUT_HEADER = "Position, Lane, Horse name";

    public String format(Map<Integer, RaceHorse> raceHorsesCurrentPosition) {
        List<RaceHorse> raceOutcome = buildOrderedList(raceHorsesCurrentPosition);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTPUT_HEADER);
        int position=1;
        for (RaceHorse raceHorse : raceOutcome) {
            stringBuilder.append(LINE_SEPARATOR)
                    .append(position)
                    .append(FIELD_SEPARATOR)
                    .append(raceHorse.lane())
                    .append(FIELD_SEPARATOR)
                    .append(raceHorse.name());
            position++;
        }

        return stringBuilder.toString();
    }

    private List<RaceHorse> buildOrderedList(Map<Integer, RaceHorse> raceHorsesCurrentPosition) {
        List<RaceHorse> raceOutcome = newArrayList(raceHorsesCurrentPosition.values());
        sort(raceOutcome, new Comparator<RaceHorse>() {
            @Override
            public int compare(RaceHorse o1, RaceHorse o2) {
                return o2.positionOnTrack() - o1.positionOnTrack();
            }
        });
        return raceOutcome;
    }
}
