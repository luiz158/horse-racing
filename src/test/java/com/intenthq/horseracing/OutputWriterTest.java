package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
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
        List<Horse> horseResultList =
                newArrayList(new Horse(2, "Bolter", 200));


        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter"));
    }

    @Test
    public void printShouldJustPrintHeaderIfEmptyList() throws Exception {
        List<Horse> noHorses = newArrayList();

        String actual = outputWriter.print(noHorses);

        assertThat(actual, equalTo("Position, Lane, Horse name"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForMultipleHorsesInCorrectOrder() {

        List<Horse> horseResultList =
                newArrayList(new Horse(2, "Bolter", 200),
                        new Horse(3, "AlwaysSecond", 150),
                        new Horse(1, "SlowCoach", 100));

        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 3, AlwaysSecond\n3, 1, SlowCoach"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForHorsesThatDraw() {
        List<Horse> horseResultList =
                newArrayList(
                        new Horse(2, "Bolter", 200),
                        new Horse(1, "Tied 2", 100),
                        new Horse(3, "AlwaysSecond", 100),
                        new Horse(4, "Should Be Fourth", 50));


        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 1, Tied 2\n2, 3, AlwaysSecond\n4, 4, Should Be Fourth"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForFourHorsesThatDraw() {
        List<Horse> horseResultList =
                newArrayList(
                        new Horse(2, "Bolter", 200),
                        new Horse(1, "Tied 2", 200),
                        new Horse(3, "AlwaysFirst", 200),
                        new Horse(4, "Should Be First", 200));


        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n1, 1, Tied 2\n1, 3, AlwaysFirst\n1, 4, Should Be First"));
    }
}