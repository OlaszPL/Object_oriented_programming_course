package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    public TextField movesTextField;
    @FXML
    public Label descriptionLabel;
    @FXML
    public GridPane mapGrid;
    private static final int CELL_WIDTH = 35;
    private static final int CELL_HEIGHT = 35;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst()); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void drawMap(){
        clearGrid();
        Boundary boundary = map.getCurrentBounds();

        for (int x = boundary.lowerLeft().getX(); x <= boundary.upperRight().getX() + 1; x++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int y = boundary.lowerLeft().getY(); y <= boundary.upperRight().getY() + 1; y++){
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        // dodanie oznaczeń osi
        Label sign = new Label("y/x");
        GridPane.setHalignment(sign, HPos.CENTER);
        GridPane.setValignment(sign, VPos.CENTER);
        mapGrid.add(sign , 0, 0);

        for (int x = boundary.lowerLeft().getX(); x <= boundary.upperRight().getX(); x++) {
            Label label = new Label(Integer.toString(x));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            mapGrid.add(label, x - boundary.lowerLeft().getX() + 1, 0);
        }
        for (int y = boundary.lowerLeft().getY(); y <= boundary.upperRight().getY(); y++) {
            Label label = new Label(Integer.toString(y));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            mapGrid.add(label, 0, y - boundary.lowerLeft().getY() + 1);
        }

        for (WorldElement element : map.getElements()){
            Vector2d pos = element.getPosition();
            Vector2d mapPos = new Vector2d(pos.getX() - boundary.lowerLeft().getX() + 1,
                    pos.getY() - boundary.lowerLeft().getY() + 1);

            WorldElement object = map.objectAt(pos).orElse(null);
            Label label = new Label(object.toString());

            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);

            mapGrid.add(label, mapPos.getX(), mapPos.getY());
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            descriptionLabel.setText(message);
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        AbstractWorldMap map = new GrassField(10);
        setWorldMap(map);
        map.registerObserver(this);
        map.registerObserver(((worldMap, message) -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.printf("%s %s%n", timestamp, message);
        }));

        List<MoveDirection> directions = OptionsParser.parse(movesTextField.getText().split(" "));

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, map);

        // uruchamiam jedną symulację, więc użycie SimulationEngine jest zbędne
        new Thread(simulation).start();
    }

}
