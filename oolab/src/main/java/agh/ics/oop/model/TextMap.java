package agh.ics.oop.model;

import java.util.ArrayList;

public class TextMap implements WorldNumberPositionMap<String, Integer> {
    private final ArrayList<String> list;

    public TextMap() {
        this.list = new ArrayList<>();
    }

    @Override
    public boolean isOccupied(Integer position) {
        return 0 <= position && position < list.size();
    }

    @Override
    public String objectAt(Integer position) {
        if (isOccupied(position)) return list.get(position);
        return null;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return isOccupied(position);
    }

    @Override
    public boolean place(String object) {
        list.add(object);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        int i = list.indexOf(object);
        if (i < 0) return;

        int newPos = i + switch(direction){
            case FORWARD, RIGHT -> 1;
            case BACKWARD, LEFT -> -1;
        };

        if (canMoveTo(newPos)){
            String toSwap = list.get(newPos);
            list.set(newPos, object);
            list.set(i, toSwap);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}