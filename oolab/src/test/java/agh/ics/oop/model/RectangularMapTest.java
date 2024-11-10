package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void objectShouldBePlacedAtPosition(){
        // given
        RectangularMap map = new RectangularMap(3, 3);
        Animal animal = new Animal(new Vector2d(1, 2));

        // then
        assertTrue(map.place(animal));
        assertEquals(animal, map.objectAt(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(1,2)));
    }

    @Test
    void objectShouldNotBePlacedAtPosition(){
        // given
        RectangularMap map = new RectangularMap(3, 5);
        Animal animal = new Animal(new Vector2d(-5, 16));

        // then
        assertFalse(map.place(animal));
        assertNull(map.objectAt(new Vector2d(-5, 16)));
        assertFalse(map.isOccupied(new Vector2d(-5, 16)));
    }

    @Test
    void objectShouldMove(){
        // given
        RectangularMap map = new RectangularMap(2, 6);
        Animal animal = new Animal(new Vector2d(0, 3));
        map.place(animal);

        // when
        MoveDirection direction = MoveDirection.FORWARD;
        map.move(animal, direction);

        // then
        assertEquals(animal, map.objectAt(new Vector2d(0, 4)));

    }

    @Test
    void objectShouldNotMove(){
        // given
        RectangularMap map = new RectangularMap(2, 2);
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);
        MoveDirection direction = MoveDirection.FORWARD;

        // when
        map.move(animal, direction);

        // then
        assertEquals(animal, map.objectAt(new Vector2d(1, 1)));

    }

    @Test
    void shouldDetectCollisionAndNotMove(){
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(1, 0));
        MoveDirection direction = MoveDirection.FORWARD;
        map.place(animal1);
        map.place(animal2);

        // when
        map.move(animal2, direction);

        // then
        assertEquals(animal2, map.objectAt(new Vector2d(1, 0)));

    }
}