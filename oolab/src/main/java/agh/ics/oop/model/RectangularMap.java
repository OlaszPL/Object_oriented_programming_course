package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        super(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    @Override
    public String toString() {
        return super.vis.draw(super.lowerLeftBound, super.upperRightBound);
    }
}