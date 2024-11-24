package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<>();
    private final WorldMap map;

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap map){
        this.moves = moves;
        this.map = map;
        for (Vector2d position : positions){
            Animal newAnimal = new Animal(position);
            try {
                this.map.place(newAnimal);
                animals.add(newAnimal);
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void run(){
        for (int i = 0; i < moves.size(); i++){
            int animal_idx = i % animals.size();
            map.move(animals.get(animal_idx), moves.get(i));
            System.out.println(map);
        }
    }
}