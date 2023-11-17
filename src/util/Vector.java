package src.util;

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

    /**
     * Calculates the Euclidean distance between the two vectors.
     * @param other Other vector to calculate distance to.
     * @return Distance between the two vectors.
     */
    public double distance(Vector other) {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2) + Math.pow(getY() - other.getY(), 2));
    }


    /**
     * Checks if this Vector is equal to another.
     * Compares X and Y component of two vectors, uses super.equals for all other objects.
     * @param obj Object to compare with.
     * @return Whether this Vector is equal to the provided object.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector v) {
            return Math.abs(this.getX() - v.getX()) < 0.00001
                && Math.abs(this.getY() - v.getY()) < 0.00001;
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}
