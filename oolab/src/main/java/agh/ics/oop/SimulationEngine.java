package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulationList;
    private final List<Thread> threads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
    }

    public void runSync(){
        for (Simulation simulation : simulationList){
            simulation.run();
        }
    }

    public void runAsync(){
        for (Simulation simulation : simulationList){
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
        awaitSimulationsEnd();
    }

    public void awaitSimulationsEnd(){
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.printf("Thread interrupted! -> %s%n", e.getMessage());
            }
        }
    }
}
