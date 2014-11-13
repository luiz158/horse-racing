package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OutputWriterTest {

    private OutputWriter outputWriter;

    @Before
    public void setUp() throws Exception {
        outputWriter = new OutputWriter();
    }

    @Test
    public void printShouldPrintOutCorrectOutputForOneHorse() {
        final HashMap<Integer, Horse> horses = new HashMap<>();
        horses.put(new Integer(1), new Horse("Star"));

        final String actual = outputWriter.print(horses);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 1, Star"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForMultipleHorsesInCorrectOrder() {

        final HashMap<Integer, Horse> horses = new HashMap<>();
        horses.put(new Integer(1), new Horse("SlowCoach", 100));
        horses.put(new Integer(2), new Horse("Bolter", 200));
        horses.put(new Integer(3), new Horse("AlwaysSecond", 150));

        final String actual = outputWriter.print(horses);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 3, AlwaysSecond\n3, 1, SlowCoach"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForHorsesThatDraw() {

        final HashMap<Integer, Horse> horses = new HashMap<>();
        horses.put(new Integer(1), new Horse("Tied 2", 100));
        horses.put(new Integer(2), new Horse("Bolter", 200));
        horses.put(new Integer(3), new Horse("AlwaysSecond", 100));
        horses.put(new Integer(4), new Horse("Should Be Fourth", 50));

        final String actual = outputWriter.print(horses);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 1, Tied 2\n2, 3, AlwaysSecond\n4, 4, Should Be Fourth"));
    }
}