package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> parsed = new ArrayList<>();
        for (String move : args) {
            switch(move){
                case "f" -> parsed.add(MoveDirection.FORWARD);
                case "b" -> parsed.add(MoveDirection.BACKWARD);
                case "r" -> parsed.add(MoveDirection.RIGHT);
                case "l" -> parsed.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(move + " is not legal move specification");
            }
        }
        return parsed;
    }
}
