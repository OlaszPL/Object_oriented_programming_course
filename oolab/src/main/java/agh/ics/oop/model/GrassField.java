package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField implements WorldMap{
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final MapVisualizer vis = new MapVisualizer(this);

    public GrassField(int grassFieldsNo) {
        int upperGrassBound = (int) Math.sqrt(grassFieldsNo * 10);
        Random rand = new Random();

        while (grasses.size() < grassFieldsNo){
            Vector2d newPos = new Vector2d(rand.nextInt(upperGrassBound + 1), rand.nextInt(upperGrassBound + 1));
//            +1 bo nextInt jest exclusive
            if (!grasses.containsKey(newPos)) grasses.put(newPos, new Grass(newPos));
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = animals.get(position);
        if (animal != null) return animal;

        return grasses.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
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

    public List<WorldElement> getElements(){
//      kopia wartości aby nie było problemu z błędnym stanem obiektu
        ArrayList<WorldElement> list = new ArrayList<>(List.copyOf(animals.values()));
        list.addAll(List.copyOf(grasses.values()));

        return list;
    }

    @Override
    public String toString() {
        List<WorldElement> elementsList = getElements();

        if (elementsList.isEmpty()) return "";

        Vector2d dynamicUpperBound = elementsList.getFirst().getPosition();
        Vector2d dynamicLowerBound = elementsList.getFirst().getPosition();

        for (WorldElement  element : elementsList){
            Vector2d elementPosition = element.getPosition();
            dynamicLowerBound = dynamicLowerBound.lowerLeft(elementPosition);
            dynamicUpperBound = dynamicUpperBound.upperRight(elementPosition);
        }

        return vis.draw(dynamicLowerBound, dynamicUpperBound);
    }
}