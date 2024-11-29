package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulationList;
    private final List<Thread> threads = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(4);

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

    public void runAsyncInThreadPool(){
        for (Simulation simulation : simulationList){
            executorService.submit(simulation);
        }
        awaitSimulationsEnd();
    }

    public void awaitSimulationsEnd(){
        try {
            for (Thread thread : threads) {
                thread.join();
            }

            executorService.shutdown();

            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }

        } catch (InterruptedException e) {
            System.out.printf("Thread interrupted! -> %s%n", e.getMessage());
        }
    }
}