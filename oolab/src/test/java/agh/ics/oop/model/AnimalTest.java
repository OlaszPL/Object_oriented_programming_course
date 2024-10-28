package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AnimalTest {

    @Test
    void animalShouldHaveProperDirection(){
        // given
        Animal animal = new Animal();

        // when
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.BACKWARD);

        // then
        assertEquals(animal.getDirection(), MapDirection.EAST);
    }

    @Test
    void animalShouldMoveToProperPosition(){
        // given
        Animal animal = new Animal(new Vector2d(1,1));

        // when
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);

        // then
        assertEquals(animal.getPosition(), new Vector2d(0, 4));
    }

    @Test
    void  animalShouldObeyBoundaries(){
        // given
        Animal animal = new Animal(new Vector2d(1, 2));

        // when
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        // then
        assertTrue(animal.getPosition().follows(Animal.LOWER_LEFT)
                && animal.getPosition().precedes(Animal.UPPER_RIGHT));
        assertEquals(animal.getPosition(), new Vector2d(0, 4));
    }

}