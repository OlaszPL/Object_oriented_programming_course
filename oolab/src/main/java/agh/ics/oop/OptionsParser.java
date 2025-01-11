package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        return Stream.of(args)
                .map(move -> switch (move){
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(move + " is not legal move specification");
                })
                .collect(Collectors.toList());
    }
}