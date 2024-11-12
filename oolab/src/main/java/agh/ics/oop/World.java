package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    public static void simRectangularMap(List<MoveDirection> directions){
        List<Animal> animals = List.of(new Animal(new Vector2d(2,2)), new Animal(new Vector2d(3,4)));
        RectangularMap map = new RectangularMap(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, map);
        simulation.run();
    }

    public static void simTextMap(List<MoveDirection> directions){
        List<String> textBlocks = List.of("Orki to takie pandy".split(" "));
        TextMap map = new TextMap();

        Simulation<String, Integer> simulation = new Simulation<>(textBlocks, directions, map);
        simulation.run();
    }

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);

        simRectangularMap(directions);
        simTextMap(directions);

    }
}
