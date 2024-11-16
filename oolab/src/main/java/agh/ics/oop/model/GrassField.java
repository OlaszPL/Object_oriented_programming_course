package agh.ics.oop.model;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassFieldsNo) {
        super();
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
        WorldElement animal = super.objectAt(position);
        if (animal != null) return animal;

        return grasses.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public List<WorldElement> getElements(){
        List<WorldElement> list = super.getElements();
//      kopia wartości aby nie było problemu z błędnym stanem obiektu
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