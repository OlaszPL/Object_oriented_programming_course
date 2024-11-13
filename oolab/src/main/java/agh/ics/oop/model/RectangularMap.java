package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
//    private final int width, height;
//    atrybuty szerokości i wysokości są zbędne, ponieważ dane te dla mapy zawarte są w wektorze ZERO oraz upperRight
    public static final Vector2d ZERO = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer vis = new MapVisualizer(this);

    public RectangularMap(int width, int height){
//        this.width = width;
//        this.height = height;
        upperRight = new Vector2d(width - 1, height - 1);
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(ZERO) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.get(animal.getPosition()) == animal){ // jeżeli zwierzak jest na mapie
            animals.remove(animal.getPosition(), animal);
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal); // jeżeli ruch jest niemożliwy, to nic się nie zmieniło
        }
    }

    @Override
    public String toString() {
        return vis.draw(ZERO, upperRight);
    }
}
