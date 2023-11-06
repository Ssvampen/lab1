package src;

public enum Direction {

    NORTH(0, new Position(0, 1)),
    WEST(1, new Position(-1, 0)),
    SOUTH(2, new Position(0, -1)),
    EAST(3, new Position(1, 0));

    private final int index;
    private final Position vector;
    private static final Direction[] VALUES = Direction.values();

    Direction(int index, Position vector){
       this.index = index;
       this.vector = vector;
    }

    public int getIndex() {
        return index;
    }

    public Position getVector() {
        return vector;
    }

    public static Direction fromIndex(int index){
        return VALUES[index];
    }

}
