package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Vector2d lowerLeftBound, upperRightBound;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer vis = new MapVisualizer(this);

    public AbstractWorldMap(Vector2d lowerLeftBound, Vector2d upperRightBound) {
        this.lowerLeftBound = lowerLeftBound;
        this.upperRightBound = upperRightBound;
    }

    public AbstractWorldMap(){
        this(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public Vector2d getLowerLeftBound() {
        return lowerLeftBound;
    }

    public Vector2d getUpperRightBound() {
        return upperRightBound;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeftBound) && position.precedes(upperRightBound) && !isOccupied(position);
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
    public List<WorldElement> getElements(){
//      kopia wartości aby nie było problemu z błędnym stanem obiektu
        return new ArrayList<>(List.copyOf(animals.values()));
    }
}