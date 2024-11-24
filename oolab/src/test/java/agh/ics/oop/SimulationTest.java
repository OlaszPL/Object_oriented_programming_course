package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class SimulationTest {

    @Test
    void shouldMoveTwoAnimalsCorrectlyWhenGivenValidDirections(){
        // given
        String []args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> expectedArgs = Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD);

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        MapDirection[] expected_directions = {MapDirection.SOUTH, MapDirection.NORTH};
        Vector2d[] expected_positions = {new Vector2d(2,0), new Vector2d(3,4)};

        RectangularMap map = new RectangularMap(5, 5);
        map.registerObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);

        // when
        simulation.run();

        // then
        List<Animal> animals = simulation.getAnimals();

        assertEquals(directions, expectedArgs);
        assertEquals(2, animals.size());

        for (int i = 0; i < animals.size(); i++){
            assertTrue(animals.get(i).getPosition().follows(map.getCurrentBounds().lowerLeft()) &&
                    animals.get(i).getPosition().precedes(map.getCurrentBounds().upperRight()));
            assertTrue(animals.get(i).isAt(expected_positions[i]));
            assertEquals(animals.get(i), map.objectAt(expected_positions[i]));
            assertEquals(animals.get(i).getDirection(), expected_directions[i]);
        }
    }

    @Test
    void shouldKeepThreeAnimalsInInitialPositionsWhenGivenInvalidDirections(){
        // given
        String []args = {"fb", "bgh", "rr", "u", "a", "mm"};
        List<MoveDirection> expectedArgs = new ArrayList<>();
        List<MoveDirection> directions;

        try {
            directions = OptionsParser.parse(args);
        } catch (RuntimeException e) {
            System.out.println("Invalid argument was given!");
            directions = new ArrayList<>(); // aby sprawdzić dalszą część symulacji
        }
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4),
                new Vector2d(1,1));

        RectangularMap map = new RectangularMap(5, 5);
        map.registerObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);

        // when
        simulation.run();

        // then
        List<Animal> animals = simulation.getAnimals();

        assertEquals(directions, expectedArgs);
        assertEquals(3, animals.size());

        for (int i = 0; i < animals.size(); i++){
            assertTrue(animals.get(i).getPosition().follows(map.getCurrentBounds().lowerLeft()) &&
                    animals.get(i).getPosition().precedes(map.getCurrentBounds().upperRight()));
            assertTrue(animals.get(i).isAt(positions.get(i)));
            assertEquals(animals.get(i), map.objectAt(positions.get(i)));
            assertEquals(MapDirection.NORTH, animals.get(i).getDirection());
        }
    }

    @Test
    void shouldHandleValidAndInvalidDirectionsForFourAnimals(){
        // given
        String []args = {"mn", "bb", "f", "m", "r", "l", "b", "f", "u", "f", "f", "b"};
        List<MoveDirection> directions;
        List<MoveDirection> expectedArgs = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD);
        try {
            directions = OptionsParser.parse(args);
        } catch (RuntimeException e) {
            System.out.println("Invalid argument was given!");
            directions = expectedArgs; // aby sprawdzić dalszą część symulacji
        }
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(4,4),
                new Vector2d(3, 2), new Vector2d(2, 1));

        MapDirection[] expected_directions = {MapDirection.NORTH, MapDirection.EAST, MapDirection.WEST,
        MapDirection.NORTH};
        Vector2d[] expected_positions = {new Vector2d(0, 2), new Vector2d(4, 4),
        new Vector2d(2, 2), new Vector2d(2, 0)};

        RectangularMap map = new RectangularMap(5, 5);
        map.registerObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);

        // when
        simulation.run();

        // then
        List<Animal> animals = simulation.getAnimals();

        assertEquals(directions, expectedArgs);
        assertEquals(4, animals.size());

        for (int i = 0; i < animals.size(); i++){
            assertTrue(animals.get(i).getPosition().follows(map.getCurrentBounds().lowerLeft()) &&
                    animals.get(i).getPosition().precedes(map.getCurrentBounds().upperRight()));
            assertTrue(animals.get(i).isAt(expected_positions[i]));
            assertEquals(animals.get(i), map.objectAt(expected_positions[i]));
            assertEquals(animals.get(i).getDirection(), expected_directions[i]);
        }
    }
}