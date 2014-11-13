package com.intenthq.horseracing;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class HorseRacingService {
    private InputProcessor inputProcessor;
    private OutputWriter outputWriter;

    @Autowired
    public HorseRacingService(final InputProcessor inputProcessor,
                              final OutputWriter outputWriter) {

        this.inputProcessor = inputProcessor;
        this.outputWriter = outputWriter;
    }

    public String processRace(final String raceInput) {
        final Map<Integer, Horse> horses = inputProcessor.parse(raceInput);
        List<Map.Entry<Integer, Horse>> sortedHorseList = sortHorses(horses);
        return outputWriter.print(sortedHorseList);
    }

    private List<Map.Entry<Integer, Horse>> sortHorses(final Map<Integer, Horse> horses) {
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
}
