package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Animal> animals = List.of(new Animal(new Vector2d(2,2)), new Animal(new Vector2d(3,4)));
        RectangularMap map = new RectangularMap(5, 5);

        for (Animal animal : animals) {
            map.place(animal);
        }

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, map);
        simulation.run();
    }
}
