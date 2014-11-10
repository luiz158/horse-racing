package com.intenthq.horseracing.engine;

import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.exceptions.RacingException;

import java.util.List;
import java.util.Map;

public class RacingEngine {
    private Map<Integer, RaceHorse> raceHorses;
    private boolean hasEnded;
    private int raceLength;

    public RacingEngine(int raceLength) {
        this.raceLength = raceLength;
    }

    public void setupCourse(Map<Integer, RaceHorse> raceHorses) {
        this.raceHorses = raceHorses;
    }

    public Map<Integer, RaceHorse> runPlays(List<Play> plays) {
        validateRaceIsSetup();
        for (int i = 0; i < plays.size() && !hasEnded; i++) {
            runPlay(plays.get(i));
        }
        return raceHorses;
    }

    private Map<Integer, RaceHorse> runPlay(Play play) {
        if(play.lane()<= raceHorses.size()){
            RaceHorse raceHorse = raceHorses.get(play.lane());
            raceHorse.advance(play.yards());
            hasEnded = (raceHorse.positionOnTrack() >= raceLength);
        }
        return raceHorses;
    }

    private void validateRaceIsSetup() {
        if(raceHorses==null)
            throw new RacingException("Race not setup properly, cannot run plays");
    }
}
