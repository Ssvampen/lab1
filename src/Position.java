package src;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position zero(){
        return new Position(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position add(Position position){
        return new Position(getX() + position.getX(), getY() + position.getY());
    }

    public Position multiply(Position position){
        return new Position(getX() * position.getX(), getY() * position.getY());
    }
}
