package com.intenthq.horseracing.engine;

import com.google.common.collect.ImmutableMap;
import com.intenthq.horseracing.entities.Play;
import com.intenthq.horseracing.entities.RaceHorse;
import com.intenthq.horseracing.exceptions.RacingException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.intenthq.horseracing.HorseRacing.FURLONG;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RacingEngineTest {

    @Test
    public void shouldSetupInitialRaceState() throws Exception {
        Map<Integer, RaceHorse> raceHorses = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(FURLONG);
        racingEngine.setupCourse(someRaceHorses());
        List<Play> emptyPlayList = emptyPlayList();

        assertThat(racingEngine.runPlays(emptyPlayList), is(raceHorses));
    }

    @Test
    public void shouldThrowRacingExceptionWhenTryingToRunPlaysOnAnUnsetRacingEngine() throws Exception{
    	try{
    		new RacingEngine(FURLONG).runPlays(emptyPlayList());
    		Assert.fail(String.format("Expected [%s]",RacingException.class.getSimpleName()));
    	} catch (RacingException expected){
            assertThat(expected.getMessage(),is("Race not setup properly, cannot run plays"));
    	}
    }

    @Test
    public void shouldAdvanceTheHorseOnThePlay() throws Exception {
        Map<Integer, RaceHorse> raceHorses = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(FURLONG);
        racingEngine.setupCourse(raceHorses);

        RaceHorse horse2 = new RaceHorse(2, "horse2");
        horse2.advance(12);
        Map<Integer, RaceHorse> expectedRaceHoses = buildRaceHorses(new RaceHorse(1, "horse1"), horse2);

        Map<Integer, RaceHorse> actualResult = racingEngine.runPlays(listWithPlay(2, 12));

        assertThat(actualResult, is(expectedRaceHoses));
    }

    @Test
    public void shouldIgnorePlaysForInvalidLanes() throws Exception{
        Map<Integer, RaceHorse> initialState = someRaceHorses();
        RacingEngine racingEngine = new RacingEngine(FURLONG);
        racingEngine.setupCourse(someRaceHorses());
        Map<Integer, RaceHorse> actualResult = racingEngine.runPlays(listWithPlay(99, 12));

        assertThat(actualResult, is(initialState));
    }

    private ArrayList<Play> listWithPlay(int lane, int yards) {
        return newArrayList(new Play(lane, yards));
    }

    private Map<Integer, RaceHorse> someRaceHorses() {
        return buildRaceHorses(new RaceHorse(1, "horse1"), new RaceHorse(2, "horse2"));
    }

    private ImmutableMap<Integer, RaceHorse> buildRaceHorses(RaceHorse horse1, RaceHorse horse2) {
        return ImmutableMap.<Integer, RaceHorse>builder()
                .put(1, horse1)
                .put(2, horse2)
                .build();
    }

    private List<Play> emptyPlayList() {
        return newArrayList();
    }
}