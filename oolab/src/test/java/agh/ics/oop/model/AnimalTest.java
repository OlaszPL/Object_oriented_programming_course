package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.RectangularMap.ZERO;
import static org.junit.jupiter.api.Assertions.*;
class AnimalTest {

    @Test
    void animalShouldHaveProperDirection(){
        // given
        Animal animal = new Animal();
        MoveValidator validator = new RectangularMap(5, 5);

        // when
        animal.move(MoveDirection.RIGHT, validator);
        animal.move(MoveDirection.RIGHT, validator);
        animal.move(MoveDirection.LEFT, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.BACKWARD, validator);

        // then
        assertEquals(animal.getDirection(), MapDirection.EAST);
    }

    @Test
    void animalShouldMoveToProperPosition(){
        // given
        Animal animal = new Animal(new Vector2d(1,1));
        MoveValidator validator = new RectangularMap(5, 5);

        // when
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.FORWARD, validator);
        animal.move(MoveDirection.RIGHT, validator);
        animal.move(MoveDirection.BACKWARD, validator);

        // then
        assertTrue(animal.isAt(new Vector2d(0, 4)));
    }

    @Test
    void animalShouldObeyBoundaries(){
        // given
        Animal animal = new Animal(new Vector2d(1, 2));
        RectangularMap map = new RectangularMap(5,5);

        // when
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);

        // then
        assertTrue(animal.getPosition().follows(ZERO)
                && animal.getPosition().precedes(map.getUpperRight()));
        assertTrue(animal.isAt(new Vector2d(0, 4)));
    }

}