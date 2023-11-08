package src;

/**
 * Represents an immutable 2D vector.
 */
public class Vector {
    private final double x;
    private final double y;

    /**
     * Constructs a new vector.
     * @param x X component of the vector.
     * @param y Y component of the vector.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector with both components zeroed.
     * @return Position The new vector.
     */
    public static Vector zero(){
        return new Vector(0, 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Constructs a new Vector with the components of this vector added to the other.
     * @param vector Vector to add to this vector.
     * @return New vector.
     */
    public Vector add(Vector vector){
        return new Vector(getX() + vector.getX(), getY() + vector.getY());
    }

    /**
     * Constructs a new Vector with the components of this vector multiplied by the other.
     * @param vector Vector to multiply this vector with.
     * @return New vector.
     */
    public Vector multiply(Vector vector){
        return new Vector(getX() * vector.getX(), getY() * vector.getY());
    }
}
