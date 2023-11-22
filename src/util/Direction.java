package src.util;

/**
 * Represents one of the four cardinal directions and their corresponding movement vector.
 */
public enum Direction {

    NORTH(0, new Vector(0, 1)),
    WEST(1, new Vector(-1, 0)),
    SOUTH(2, new Vector(0, -1)),
    EAST(3, new Vector(1, 0));

    private final int index;
    private final Vector vector;

    /**
     * Static array of all values in this enum.
     */
    private static final Direction[] VALUES = Direction.values();

    /**
     * Constructs a new Direction.
     * @param index Index (order) of the direction in the enum.
     * @param vector Corresponding vector where this direction points.
     */
    Direction(int index, Vector vector){
       this.index = index;
       this.vector = vector;
    }

    public int getIndex() {
        return index;
    }

    public Vector getVector() {
        return vector;
    }

    /**
     * Returns the direction for the specified index.
     * @param index Index of direction.
     * @throws ArrayIndexOutOfBoundsException Thrown when an index out of range [0, 3] is passed.
     * @return Direction for the specified index.
     */
    public static Direction fromIndex(int index) throws ArrayIndexOutOfBoundsException {
        return VALUES[index];
    }

}
