package com.intenthq.horseracing.engine;

import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;

import java.util.Map;

public class RacingEngine {
    private final Map<Integer, RaceHorse> raceHorses;
    private boolean isEnded;
    private int raceLength;

    public RacingEngine(Map<Integer, RaceHorse> raceHorses, int raceLength) {
        this.raceHorses = raceHorses;
        this.raceLength = raceLength;
    }

    public Map<Integer, RaceHorse> runPlay(Play play) {
        if(play.lane()<= raceHorses.size()){
            RaceHorse raceHorse = raceHorses.get(play.lane());
            raceHorse.advance(play.yards());
            isEnded = (raceHorse.positionOnTrack() >= raceLength);
        }
        return raceHorses;
    }

    public Map<Integer, RaceHorse> currentState() {
        return raceHorses;
    }

    public boolean isEnded() {
        return isEnded;
    }
}
