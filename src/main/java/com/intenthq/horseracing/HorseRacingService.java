package com.intenthq.horseracing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        return outputWriter.print(horses);
    }
}
