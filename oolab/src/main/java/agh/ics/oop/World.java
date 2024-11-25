package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    public static void simRectangularMap(List<MoveDirection> directions){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(100, 100));
        RectangularMap map = new RectangularMap(5, 5);
        map.registerObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

    public static void simGrassField(List<MoveDirection> directions, int grassFieldsNo){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField map = new GrassField(grassFieldsNo);
        map.registerObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            simRectangularMap(directions);
            simGrassField(directions, 10);
        } catch (IllegalArgumentException e) {
            System.out.printf("Given illegal argument: %s%n", e.getMessage());
        }
    }
}