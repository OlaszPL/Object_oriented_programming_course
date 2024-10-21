package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] parsed = new MoveDirection[args.length];
        int last = 0;
        for (String move : args) {
            MoveDirection to_ins = switch(move){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if (to_ins != null) {
                parsed[last] = to_ins;
                last++;
            }
        }
        return Arrays.copyOfRange(parsed, 0, last);
    }
}
