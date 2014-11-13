package com.intenthq.horseracing;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.intenthq.horseracing.TestHelper.createHorseEntry;
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
        List<Map.Entry<Integer, Horse>> horseResultList =
                newArrayList(createHorseEntry(2, "Bolter", 200));


        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter"));
    }

    @Test
    public void printShouldJustPrintHeaderIfEmptyList() throws Exception {
        List<Map.Entry<Integer, Horse>> noHorses = newArrayList();

        String actual = outputWriter.print(noHorses);

        assertThat(actual, equalTo("Position, Lane, Horse name"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForMultipleHorsesInCorrectOrder() {

        List<Map.Entry<Integer, Horse>> horseResultList =
                newArrayList(createHorseEntry(2, "Bolter", 200),
                        createHorseEntry(3, "AlwaysSecond", 150),
                        createHorseEntry(1, "SlowCoach", 100));

        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 3, AlwaysSecond\n3, 1, SlowCoach"));
    }

    @Test
    public void printShouldPrintOutCorrectOutputForHorsesThatDraw() {

        List<Map.Entry<Integer, Horse>> horseResultList =
                newArrayList(
                        createHorseEntry(2, "Bolter", 200),
                        createHorseEntry(1, "Tied 2", 100),
                        createHorseEntry(3, "AlwaysSecond", 100),
                        createHorseEntry(4, "Should Be Fourth", 50));


        final String actual = outputWriter.print(horseResultList);

        assertThat(actual, equalTo("Position, Lane, Horse name\n1, 2, Bolter\n2, 1, Tied 2\n2, 3, AlwaysSecond\n4, 4, Should Be Fourth"));
    }
}