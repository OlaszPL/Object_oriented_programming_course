package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private static final Vector2d ZERO = new Vector2d(0,0);

    public GrassField(int grassFieldsNo) {
        super();
        int upperGrassBound = (int) Math.sqrt(grassFieldsNo * 10);

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperGrassBound + 1, upperGrassBound + 1, grassFieldsNo);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
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
        return Stream.concat(super.getElements().stream(), grasses.values().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds() {
        List<WorldElement> elementsList = getElements();

        if (elementsList.isEmpty()) return new Boundary(ZERO, ZERO);

        Vector2d dynamicUpperBound = elementsList.getFirst().getPosition();
        Vector2d dynamicLowerBound = elementsList.getFirst().getPosition();

        for (WorldElement  element : elementsList){
            Vector2d elementPosition = element.getPosition();
            dynamicLowerBound = dynamicLowerBound.lowerLeft(elementPosition);
            dynamicUpperBound = dynamicUpperBound.upperRight(elementPosition);
        }

        return new Boundary(dynamicLowerBound, dynamicUpperBound);
    }
}