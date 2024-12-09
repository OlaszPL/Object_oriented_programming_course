package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        AbstractWorldMap map = new GrassField(10);
        SimulationPresenter presenter = loader.getController();
        presenter.setWorldMap(map);
        map.registerObserver(presenter);
        List<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        Simulation simulation = new Simulation(List.of(new Vector2d(2,2), new Vector2d(3,4)), directions, map);
        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }
}
