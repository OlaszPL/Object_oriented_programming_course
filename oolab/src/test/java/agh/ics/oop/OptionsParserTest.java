package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @Test
    void parsedShouldBeForward(){
        // given
        String[] args = {"f"};
        List<MoveDirection> expected = new ArrayList<>();
        expected.add(MoveDirection.FORWARD);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeBackward(){
        // given
        String[] args = {"b"};
        List<MoveDirection> expected = new ArrayList<>();
        expected.add(MoveDirection.BACKWARD);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeLeft(){
        // given
        String[] args = {"l"};
        List<MoveDirection> expected = new ArrayList<>();
        expected.add(MoveDirection.LEFT);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeRight(){
        // given
        String[] args = {"r"};
        List<MoveDirection> expected = new ArrayList<>();
        expected.add(MoveDirection.RIGHT);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeNullForUnknownDirections(){
        // given
        String[] args = {"s", "bb", "B"};
        List<MoveDirection> expected = new ArrayList<>();

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }


    @Test
    void parsedShouldContainMultipleDirection(){
        // given
        String[] args = {"f", "b", "l", "r"};
        List<MoveDirection> expected = Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }

    @Test
    void parsedShouldContainMultipleDirectionAndSkipUnknown(){
        // given
        String[] args = {"f", "s", "c", "b", "x", "l", "r", "m", "sth"};
        List<MoveDirection> expected = Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT);

        // when
        List<MoveDirection> parsed = OptionsParser.parse(args);

        // then
        assertEquals(expected, parsed);
    }
}