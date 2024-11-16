package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {

    public static void simRectangularMap(List<MoveDirection> directions){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

    public static void simGrassField(List<MoveDirection> directions, int grassFieldsNo){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField map = new GrassField(grassFieldsNo);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);

        simRectangularMap(directions);
        simGrassField(directions, 10);
    }
}
