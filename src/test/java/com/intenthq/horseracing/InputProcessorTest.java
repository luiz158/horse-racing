package com.intenthq.horseracing;

import com.intenthq.horseracing.exception.BallTossInvalidException;
import com.intenthq.horseracing.exception.NoValidHorsesException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InputProcessorTest {

    private InputProcessor inputProcessor;
    @Mock
    private InputValidator validator;

    @Before
    public void setUp() throws Exception {
        inputProcessor = new InputProcessor(validator);
    }

    @Test(expected = NoValidHorsesException.class)
    public void processShouldPropagateNoValidHorsesExceptionIfThrownByValidation() throws Exception {
        doThrow(NoValidHorsesException.class).when(validator).validateHorses(anyString());
        inputProcessor.process("Star, buddy, Ace\n1 60\n");
    }

    @Test
    public void processShouldSendHorseLineToValidateHorsesMethod() throws Exception {
        inputProcessor.process("Star, buddy, Ace\n1 60\n");
        verify(validator).validateHorses("Star, buddy, Ace");
    }

    @Test
    public void processShouldReturnMapOfHorses() throws Exception {

        Map<Integer, Horse> expected = newHashMap();
        expected.put(new Integer(1), new Horse(1, "Star", 0));
        expected.put(new Integer(2), new Horse(2, "buddy", 0));
        expected.put(new Integer(3), new Horse(3, "Ace", 0));

        Map<Integer, Horse> actual = inputProcessor.process("Star, buddy, Ace\n");

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void processShouldHandleWhiteSpaceInconsistencies() throws Exception {

        Map<Integer, Horse> expected = newHashMap();
        expected.put(new Integer(1), new Horse(1, "Star", 0));
        expected.put(new Integer(2), new Horse(2, "buddy", 0));
        expected.put(new Integer(3), new Horse(3, "Ace", 0));

        Map<Integer, Horse> actual = inputProcessor.process("Star,buddy, Ace\n");

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void processShouldSendBallTossLineToValidateBallTossMethod() throws Exception {
        inputProcessor.process("Star, buddy, Ace\n1 60\n");
        verify(validator).validateBallToss("1 60", 3);
    }

    @Test
    public void processShouldAddDistanceToHorse() throws Exception {
        Map<Integer, Horse> expected = newHashMap();
        expected.put(new Integer(1), new Horse(1, "Star", 100));
        expected.put(new Integer(2), new Horse(2, "Bar", 10));

        Map<Integer, Horse> actual = inputProcessor.process("Star, Bar\n1 60\n1 20\n2 10\n1 20\n");

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void processShouldIgnoreBallTossLineIfInvalid() throws Exception {
        Map<Integer, Horse> expected = newHashMap();
        expected.put(new Integer(1), new Horse(1, "Star", 100));
        expected.put(new Integer(2), new Horse(2, "Bar", 10));

        doThrow(BallTossInvalidException.class).when(validator).validateBallToss(eq("7 43"), eq(2));

        Map<Integer, Horse> actual = inputProcessor.process("Star, Bar\n1 60\n7 43\n1 20\n2 10\n1 20\n");

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void processShouldReturnMapWhenAHorseGoesAFurlong() throws Exception {
        Map<Integer, Horse> expected = newHashMap();
        expected.put(new Integer(1), new Horse(1, "Star", 220));
        expected.put(new Integer(2), new Horse(2, "Bar", 20));

        Map<Integer, Horse> actual = inputProcessor.process("Star, Bar\n1 200\n2 20\n1 20\n2 10\n2 20\n1 5\n");

        assertThat(actual, equalTo(expected));
    }
}
