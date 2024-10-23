package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class Vector2dTest {

    @Test
    void equalsOfTwoVectorsTrue(){
        // when
        Vector2d v1 = new Vector2d(3, 5);
        Vector2d v2 = new Vector2d(3, 5);

        // then
        assertTrue(v1.equals(v2));
    }

    @Test
    void equalsOfTwoVectorsFalse(){
        // when
        Vector2d v1 = new Vector2d(3, 5);
        Vector2d v2 = new Vector2d(1, 1);

        // then
        assertFalse(v1.equals(v2));
    }

    @Test
    void toStringOutcomeCheck() {
        // given
        Vector2d v1 = new Vector2d(6, 6);

        // when
        String result = v1.toString();

        // then
        assertEquals(result, "(6,6)");
    }

    @Test
    void precedesTrue(){
        // when
        Vector2d v1 = new Vector2d(-1, 1);
        Vector2d v2 = new Vector2d(3, 1);

        // then
        assertTrue(v1.precedes(v2));
    }

    @Test
    void precedesFalse(){
        // when
        Vector2d v1 = new Vector2d(4, 1);
        Vector2d v2 = new Vector2d(3, 1);

        // then
        assertFalse(v1.precedes(v2));
    }

    @Test
    void followsTrue(){
        // when
        Vector2d v1 = new Vector2d(4, 1);
        Vector2d v2 = new Vector2d(3, 1);

        // then
        assertTrue(v1.follows(v2));
    }

    @Test
    void followsFalse(){
        // when
        Vector2d v1 = new Vector2d(-1, 1);
        Vector2d v2 = new Vector2d(3, 1);

        // then
        assertFalse(v1.follows(v2));
    }

    @Test
    void upperRightCheck(){
        // given
        Vector2d v1 = new Vector2d(4, 1);
        Vector2d v2 = new Vector2d(3, 5);

        // when
        Vector2d upperRight = v1.upperRight(v2);

        // then
        assertEquals(upperRight, new Vector2d(4, 5));
    }

    @Test
    void lowerLeftCheck(){
        // given
        Vector2d v1 = new Vector2d(-1, 1);
        Vector2d v2 = new Vector2d(3, -15);

        // when
        Vector2d lowerLeft = v1.lowerLeft(v2);

        // then
        assertEquals(lowerLeft, new Vector2d(-1, -15));
    }

    @Test
    void addTwoVectors(){
        // given
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(3, -7);

        // when
        Vector2d result = v1.add(v2);

        // then
        assertEquals(result, new Vector2d(5, -6));
    }

    @Test
    void subtractTwoVectors(){
        // given
        Vector2d v1 = new Vector2d(4, 1);
        Vector2d v2 = new Vector2d(3, 7);

        // when
        Vector2d result = v1.subtract(v2);

        // then
        assertEquals(result, new Vector2d(1, -6));
    }

    @Test
    void oppositeVector(){
        // given
        Vector2d v1 = new Vector2d(4, 7);

        // when
        Vector2d opposite = v1.opposite();

        // then
        assertEquals(opposite, new Vector2d(-4, -7));
    }
}