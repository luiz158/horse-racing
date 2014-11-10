package com.intenthq.horseracing.engine;

import com.google.common.collect.ImmutableMap;
import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RacingEngineTest {

    @Test
    public void shouldSetupInitialRaceState() throws Exception {
        Map<Integer, RaceHorse> raceHorses = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(raceHorses, 220);

        assertThat(racingEngine.currentState(), is(raceHorses));
    }

    @Test
    public void shouldAdvanceTheHorseOnThePlay() throws Exception {
        Map<Integer, RaceHorse> raceHorses = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(raceHorses, 220);
        racingEngine.runPlay(new Play(2, 12));

        RaceHorse horse2 = new RaceHorse(2, "horse2");
        horse2.advance(12);
        Map<Integer, RaceHorse> expectedRaceHoses = ImmutableMap.<Integer, RaceHorse>builder()
                .put(1, new RaceHorse(1, "horse1"))
                .put(2, horse2)
                .build();

        assertThat(racingEngine.currentState(), is(expectedRaceHoses));
    }

    @Test
    public void shouldIgnorePlaysForInvalidLanes() throws Exception{
        Map<Integer, RaceHorse> initialState = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(initialState, 220);
        racingEngine.runPlay(new Play(99, 12));

        assertThat(racingEngine.currentState(), is(initialState));
    }

    private Map<Integer, RaceHorse> someRaceHorses() {
        return ImmutableMap.<Integer, RaceHorse>builder()
                .put(1, new RaceHorse(1, "horse1"))
                .put(2, new RaceHorse(2, "horse2"))
                .build();
    }

}