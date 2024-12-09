package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    public TextField movesTextField;
    public Label descriptionLabel;
    private WorldMap map;
    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    public void drawMap(){
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            descriptionLabel.setText(message);
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        AbstractWorldMap map = new GrassField(10);
        setWorldMap(map);
        map.registerObserver(this);

        List<MoveDirection> directions = OptionsParser.parse(movesTextField.getText().split(" "));

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));

        // dzięki temu executorService.shutdown(); nie zamknie puli wątków, w której działa JavaFX
        // - będą one w oddzielnych pulach
        new Thread(engine::runAsyncInThreadPool).start();
    }

}
