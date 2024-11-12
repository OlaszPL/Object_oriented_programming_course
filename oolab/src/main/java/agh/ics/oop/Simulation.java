package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<MoveDirection> moves;
    private final List<T> objectsOnMap = new ArrayList<>();
    private final WorldMap<T, P> map;

    public List<T> getObjectsOnMap() {
        return objectsOnMap;
    }

    //  Wymaga wcześniejszej inicjalizacji listy obiektów.
    //  Nie da się tego tutaj zrealizować, gdyż typy generyczne nie posiadają konstruktora.
    public Simulation(List<T> objects, List<MoveDirection> moves, WorldMap<T, P> map){
        this.moves = moves;
        this.map = map;
        for (T object : objects){
            if (map.place(object)) objectsOnMap.add(object);
        }
    }

    public void run(){
        for (int i = 0; i < moves.size(); i++){
            int object_idx = i % objectsOnMap.size();
            map.move(objectsOnMap.get(object_idx), moves.get(i));
            System.out.println(map);
        }
    }
}