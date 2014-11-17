package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class HorseTest {

    private Horse horse;

    @Before
    public void setUp() throws Exception {
        horse = new Horse(1, "Star");
    }

    @Test
    public void addYardsShouldAddYardsToYardsTravelled() throws Exception {
        int yards = 20;
        horse.addYards(yards);
        assertThat(horse.getYardsCovered(), equalTo(yards));
    }

    @Test
    public void isFinishedShouldReturnFalseIfYardsCoveredIsLessThanAFurlong() throws Exception {
        horse.addYards(219);
        assertFalse(horse.isFinished());
    }

    @Test
    public void isFinishedShouldReturnTrueIfYardsCoveredIsAFurlong() throws Exception {
        horse.addYards(220);
        assertTrue(horse.isFinished());
    }
}
