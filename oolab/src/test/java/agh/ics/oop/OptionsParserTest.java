package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parsedShouldBeForward(){
        // given
        String[] args = {"f"};
        MoveDirection[] expected = {MoveDirection.FORWARD};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeBackward(){
        // given
        String[] args = {"b"};
        MoveDirection[] expected = {MoveDirection.BACKWARD};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeLeft(){
        // given
        String[] args = {"l"};
        MoveDirection[] expected = {MoveDirection.LEFT};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeRight(){
        // given
        String[] args = {"r"};
        MoveDirection[] expected = {MoveDirection.RIGHT};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }

    @Test
    void parsedShouldBeNullForUnknownDirections(){
        // given
        String[] args = {"s", "bb", "B"};
        MoveDirection[] expected = {};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }


    @Test
    void parsedShouldContainMultipleDirection(){
        // given
        String[] args = {"f", "b", "l", "r"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }

    @Test
    void parsedShouldContainMultipleDirectionAndSkipUnknown(){
        // given
        String[] args = {"f", "s", "c", "b", "x", "l", "r", "m", "sth"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};

        // when
        MoveDirection[] parsed = OptionsParser.parse(args);

        // then
        assertArrayEquals(expected, parsed);
    }
}