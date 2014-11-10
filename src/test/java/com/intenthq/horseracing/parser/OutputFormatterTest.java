package com.intenthq.horseracing.parser;

import com.google.common.collect.ImmutableMap;
import com.intenthq.horseracing.entities.RaceHorse;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OutputFormatterTest {

    @Test
    public void shouldOutputCurrentStateAsExpected() throws Exception {
        OutputFormatter formatter = new OutputFormatter();

        String expectedOutput = "Position, Lane, Horse name\n" +
                "1, 2, horse2\n" +
                "2, 1, horse1";
        assertThat(formatter.format(raceState()), is(expectedOutput));
    }

    private Map<Integer, RaceHorse> raceState() {
        RaceHorse horse1 = new RaceHorse(1, "horse1");
        horse1.advance(55);
        RaceHorse horse2 = new RaceHorse(2, "horse2");
        horse2.advance(62);
        return ImmutableMap.<Integer, RaceHorse>builder()
                .put(1, horse1)
                .put(2, horse2)
                .build();
    }
}


