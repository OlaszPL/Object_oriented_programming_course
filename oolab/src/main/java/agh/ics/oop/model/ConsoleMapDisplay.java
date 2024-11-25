package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updateCount = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.printf("MapID: %s%n", worldMap.getId());
        System.out.printf("Update Number: %d -> %s%n", ++updateCount, message);
        System.out.println(worldMap);
    }
}