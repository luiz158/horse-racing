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
    public void isFinishedShouldReturnFalseIfYardsCoveredIsLessThanOrEqualToAFurlong() throws Exception {
        horse.addYards(220);
        assertFalse(horse.isFinished());
    }

    @Test
    public void isFinishedShouldReturnTrueIfYardsCoveredIsMoreThanAFurlong() throws Exception {
        horse.addYards(221);
        assertTrue(horse.isFinished());
    }
}
