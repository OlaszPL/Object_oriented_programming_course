package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void objectShouldBePlacedAtPosition(){
        // given
        RectangularMap map = new RectangularMap(3, 3);
        Animal animal = new Animal(new Vector2d(1, 2));

        // then
        assertDoesNotThrow(() -> map.place(animal));
        assertEquals(Optional.of(animal), map.objectAt(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(1,2)));
    }

    @Test
    void objectShouldNotBePlacedAtPosition(){
        // given
        RectangularMap map = new RectangularMap(3, 5);
        Animal animal = new Animal(new Vector2d(-5, 16));

        // then
        assertThrows(IncorrectPositionException.class, () -> map.place(animal));
        assertNull(map.objectAt(new Vector2d(-5, 16)).orElse(null));
        assertFalse(map.isOccupied(new Vector2d(-5, 16)));
    }

    @Test
    void objectShouldBeAbleToMove(){
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(0, 2));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }

        // when
        MoveDirection direction = MoveDirection.FORWARD;
        animal.move(direction, map);

        // then
        assertTrue(map.canMoveTo(animal.getPosition()));
    }

    @Test
    void objectShouldNotBeAbleToMove(){
        // given
        RectangularMap map = new RectangularMap(2, 3);
        Animal animal = new Animal(new Vector2d(0, 2));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }

        // when
        MoveDirection direction = MoveDirection.FORWARD;
        animal.move(direction, map);

        // then
        assertFalse(map.canMoveTo(animal.getPosition()));
    }

    @Test
    void objectShouldMove(){
        // given
        RectangularMap map = new RectangularMap(2, 6);
        Animal animal = new Animal(new Vector2d(0, 3));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }

        // when
        MoveDirection direction = MoveDirection.FORWARD;
        map.move(animal, direction);

        // then
        assertEquals(Optional.of(animal), map.objectAt(new Vector2d(0, 4)));
        assertTrue(map.isOccupied(new Vector2d(0,4)));
    }

    @Test
    void objectShouldNotMove(){
        // given
        RectangularMap map = new RectangularMap(2, 2);
        Animal animal = new Animal(new Vector2d(1, 1));
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }
        MoveDirection direction = MoveDirection.FORWARD;

        // when
        map.move(animal, direction);

        // then
        assertEquals(Optional.of(animal), map.objectAt(new Vector2d(1, 1)));

    }

    @Test
    void shouldDetectCollisionAndNotMove(){
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(1, 0));
        MoveDirection direction = MoveDirection.FORWARD;
        try {
            map.place(animal1);
            map.place(animal2);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }

        // when
        map.move(animal2, direction);

        // then
        assertEquals(Optional.of(animal2), map.objectAt(new Vector2d(1, 0)));
    }

    @Test
    void animalsShouldBeOrderedByPosition() {
        // given
        RectangularMap map = new RectangularMap(10, 10);
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(1, 4));
        Animal animal3 = new Animal(new Vector2d(2, 2));
        Animal animal4 = new Animal(new Vector2d(5, 7));
        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
            map.place(animal4);
        } catch (IncorrectPositionException e) {
            System.out.println("Exception thrown during placement: " + e.getMessage());
        }

        // when
        Collection<Animal> orderedAnimals = map.getOrderedAnimals();

        // then
        Animal[] expectedOrder = {animal2, animal3, animal1, animal4};
        assertArrayEquals(expectedOrder, orderedAnimals.toArray());
    }
}