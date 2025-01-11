package agh.ics.oop;

import agh.ics.oop.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static Simulation simRectangularMap(List<MoveDirection> directions){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(100, 100));
        RectangularMap map = new RectangularMap(5, 5);
        map.registerObserver(new ConsoleMapDisplay());
        map.registerObserver(((worldMap, message) -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.printf("%s %s%n", timestamp, message);
        }));

        return new Simulation(positions, directions, map);
    }

    public static Simulation simGrassField(List<MoveDirection> directions, int grassFieldsNo){
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField map = new GrassField(grassFieldsNo);
        map.registerObserver(new ConsoleMapDisplay());
        map.registerObserver(((worldMap, message) -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.printf("%s %s%n", timestamp, message);
        }));

        return new Simulation(positions, directions, map);
    }

    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Simulation> simulationList = new ArrayList<>();

            for (int i = 0; i < 501; i++){
                simulationList.add(simRectangularMap(directions));
                simulationList.add(simGrassField(directions, 10));
            }

            SimulationEngine simulationEngine = new SimulationEngine(simulationList);
//            simulationEngine.runSync();
//            simulationEngine.runAsync();
            simulationEngine.runAsyncInThreadPool();
        } catch (IllegalArgumentException e) {
            System.out.printf("Given illegal argument: %s%n", e.getMessage());
            return;
        }
        System.out.println("System zakończył działanie");
    }
}