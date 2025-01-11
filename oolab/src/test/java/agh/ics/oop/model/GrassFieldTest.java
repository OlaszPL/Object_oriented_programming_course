package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void numberOfAddedGrassesShouldBeCorrect(){
        // given
        GrassField map = new GrassField(10);

        // when
        List<WorldElement> list = map.getElements();

        // then
        assertEquals(10, list.size()) ;
    }

    @Test
    void grassesShouldBeAddedInCorrectBounds(){
        // given
        GrassField map = new GrassField(10);
        int upperGrassBound = (int) Math.sqrt(10 * 10);
        Vector2d lower = new Vector2d(0, 0);
        Vector2d upper = new Vector2d(upperGrassBound, upperGrassBound);

        // when
        List<WorldElement> list = map.getElements();

        // then
        for (WorldElement grass : list){
            assertTrue(grass.getPosition().follows(lower) && grass.getPosition().precedes(upper));
        }
    }

    @Test
    void objectShouldBePlacedAtPosition(){
        // given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(1, 2));

        // then
        assertDoesNotThrow(() -> map.place(animal));
        assertEquals(animal, map.objectAt(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(1,2)));
    }

    @Test
    void objectShouldNotBePlacedAtPosition(){
        // given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(-5, 16));
        Animal prePlacedAnimal = new Animal(new Vector2d(-5, 16));

        // then
        assertDoesNotThrow(() -> map.place(prePlacedAnimal));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal));
    }

    @Test
    void objectShouldBeAbleToMove(){
        // given
        GrassField map = new GrassField(10);
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
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(0, 2));
        Animal animal2 = new Animal(new Vector2d(0, 3));
        try {
            map.place(animal);
            map.place(animal2);
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
        GrassField map = new GrassField(10);
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
        assertEquals(animal, map.objectAt(new Vector2d(0, 4)));
        assertTrue(map.isOccupied(new Vector2d(0,4)));
    }

    @Test
    void objectShouldNotMove(){
        // given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(1, 2));
        try {
            map.place(animal);
            map.place(animal2);
        } catch (IncorrectPositionException e) {
            fail("Exception thrown during first placement: " + e.getMessage());
        }
        MoveDirection direction = MoveDirection.FORWARD;

        // when
        map.move(animal, direction);

        // then
        assertEquals(animal, map.objectAt(new Vector2d(1, 1)));

    }

    @Test
    void shouldDetectCollisionAndNotMove(){
        // given
        GrassField map = new GrassField(10);
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
        assertEquals(animal2, map.objectAt(new Vector2d(1, 0)));

    }

    @Test
    void animalsShouldBeOrderedByPosition() {
        // given
        GrassField map = new GrassField(10);
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