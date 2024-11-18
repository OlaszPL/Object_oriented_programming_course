package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

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
        assertEquals(list.size(), 10) ;
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
        assertTrue(map.place(animal));
        assertEquals(animal, map.objectAt(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(1,2)));
    }

    @Test
    void objectShouldNotBePlacedAtPosition(){
        // given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(-5, 16));
        Animal prePlacedAnimal = new Animal(new Vector2d(-5, 16));

        // when
        map.place(prePlacedAnimal);

        // then
        assertFalse(map.place(animal));
    }

    @Test
    void objectShouldBeAbleToMove(){
        // given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(0, 2));
        map.place(animal);

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
        map.place(animal);
        map.place(animal2);

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
        map.place(animal);

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
        map.place(animal);
        map.place(animal2);
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
        map.place(animal1);
        map.place(animal2);

        // when
        map.move(animal2, direction);

        // then
        assertEquals(animal2, map.objectAt(new Vector2d(1, 0)));

    }
}