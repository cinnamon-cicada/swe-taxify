package sprint1;

/**
 * Represents a location in a two-dimensional coordinate system.
 * Implements the ILocation interface to provide access to x and y coordinates.
 */
public class Location implements ILocation {
    /** The x-coordinate of this location */
    private int x;
    /** The y-coordinate of this location */
    private int y;

    /**
     * Constructs a Location with the specified coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the x-coordinate of this location.
     * @return the x-coordinate as an integer
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of this location.
     * @return the y-coordinate as an integer
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Returns a string representation of this location.
     * @return a string in the format "(x,y)"
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}
