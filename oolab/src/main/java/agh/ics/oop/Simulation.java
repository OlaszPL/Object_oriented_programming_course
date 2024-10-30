package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves){
        this.moves = moves;
        for (Vector2d position : positions){
            animals.add(new Animal(position));
        }
    }

    public void run(){
        for (int i = 0; i < moves.size(); i++){
            int animal_idx = i % animals.size();
            Animal animal = animals.get(animal_idx);
            animal.move(moves.get(i));
            System.out.printf("Zwierzę %d: %s%n",animal_idx, animal);
        }
    }

}