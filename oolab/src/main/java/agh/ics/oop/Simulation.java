package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class Simulation<T, P> {
    private final List<MoveDirection> moves;
    private final List<T> objects;
    private final WorldMap<T, P> map;

//  Wymaga wcześniejszej inicjalizacji listy obiektów oraz zakłada, że są one poprawnie dodane do mapy.
//  Nie da się tego tutaj zrealizować, gdyż typy generyczne nie posiadają konstruktora, a dodawanie w tym miejscu do mapy
//  wymagałoby usuwania z listy obiektów, które z jakiegoś powodu nie mogą zostać dodane do mapy (np. mają złą pozycję).
    public Simulation(List<T> objects, List<MoveDirection> moves, WorldMap<T, P> map){
        this.moves = moves;
        this.map = map;
        this.objects = objects;
    }

    public void run(){
        for (int i = 0; i < moves.size(); i++){
            int object_idx = i % objects.size();
            map.move(objects.get(object_idx), moves.get(i));
            System.out.println(map);
        }
    }
}