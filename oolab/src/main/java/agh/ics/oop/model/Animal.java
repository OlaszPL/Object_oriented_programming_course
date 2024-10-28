package agh.ics.oop.model;

public class Animal {

    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    public static final Vector2d UPPER_RIGHT = new Vector2d(4, 4);

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
        return "Zwierzę{" +
                "orientacja=" + direction +
                ", pozycja=" + position +
                '}';
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> this.position = this.position.add(this.direction.toUnitVector());
            case BACKWARD -> this.position = this.position.subtract(this.direction.toUnitVector());
        }
        this.position = this.position.upperRight(LOWER_LEFT);
        this.position = this.position.lowerLeft(UPPER_RIGHT);
    }
}
