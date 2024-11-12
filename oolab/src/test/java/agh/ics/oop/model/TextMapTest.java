package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TextMapTest {

    @Test
    void objectShouldBePlaced(){
        // given
        TextMap map = new TextMap();
        String text = "Ala";

        // then
        assertTrue(map.place(text));
        assertEquals(text, map.objectAt(0));
        assertTrue(map.isOccupied(0));
    }

    @Test
    void objectShouldMove(){
        // given
        TextMap map = new TextMap();
        String[] text = "Ala ma kota".split(" ");

        for (String word : text) map.place(word);

        // when
        MoveDirection direction = MoveDirection.RIGHT;
        map.move(text[0], direction);

        // then
        assertEquals("Ala", map.objectAt(1));

    }

    @Test
    void objectShouldNotMove(){
        // given
        TextMap map = new TextMap();
        String[] text = "Ala ma kota".split(" ");

        for (String word : text) map.place(word);

        // when
        MoveDirection direction = MoveDirection.FORWARD;
        map.move(text[2], direction);

        // then
        assertEquals("kota", map.objectAt(2));

    }
}