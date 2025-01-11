package agh.ics.oop.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class WorldElementBox extends VBox {
    private static final Map<String, Image> imgCache = new HashMap<>();

    private static Image loadImg(String imgName){
        return imgCache.computeIfAbsent(imgName, image -> new Image(imgName));
    }

    public WorldElementBox(WorldElement element){
        Image img = loadImg(element.getElementImgName());
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        Label label = new Label(element.getPosition().toString());
        this.getChildren().addAll(imageView, label);
        this.setAlignment(Pos.CENTER);
    }
}
