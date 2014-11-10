package com.intenthq.horseracing.entities;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RaceHorseTest {

    @Test
    public void shouldCreateRaceHorseInPositionZero() throws Exception{
        String horseName = "someName";
        int horseLane = 1;
        RaceHorse raceHorse = new RaceHorse(horseLane, horseName);

        assertThat(raceHorse.name(),is(horseName));
        assertThat(raceHorse.lane(),is(horseLane));
        assertThat(raceHorse.positionOnTrack(),is(0));
    }

    @Test
    public void advancingHorseShouldAddTheNumberOfYardsToTheCurrentPosition() throws Exception{
        RaceHorse raceHorse = new RaceHorse(1, "someName");
        assertThat(raceHorse.positionOnTrack(),is(0));

        raceHorse.advance(10);
        assertThat(raceHorse.positionOnTrack(),is(10));

        raceHorse.advance(33);
        assertThat(raceHorse.positionOnTrack(),is(43));
    }
}