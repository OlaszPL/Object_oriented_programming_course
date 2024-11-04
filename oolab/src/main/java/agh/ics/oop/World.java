package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {

    public static void run(MoveDirection[] moves){
        for (MoveDirection move : moves) {
            switch(move){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }

    public static void main(String[] args) {
//        Animal animal1 = new Animal();
//        System.out.println(animal1.getPosition());

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }
}
