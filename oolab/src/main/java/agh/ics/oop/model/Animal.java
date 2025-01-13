package agh.ics.oop.model;

public class Animal implements WorldElement {
    private static final String[] IMG_NAMES = {"up.png", "down.png", "left.png", "right.png"};
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;

    public Animal(Vector2d position){
        this.position = position;
    }

    public Animal(){
        this(new Vector2d(2,2));
    }

    @Override
    public String toString() {
        return direction.toString();
    }

    @Override
    public String getElementImgName() {
        return switch (direction){
            case NORTH -> IMG_NAMES[0];
            case SOUTH -> IMG_NAMES[1];
            case WEST -> IMG_NAMES[2];
            case EAST -> IMG_NAMES[3];
        };
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction, MoveValidator validator){
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());
                if (validator.canMoveTo(newPosition)) this.position = newPosition;
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                if (validator.canMoveTo(newPosition)) this.position = newPosition;
            }
        }
    }
}