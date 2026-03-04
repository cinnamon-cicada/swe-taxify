package sprint1;

/**
 * Represents a location in a two-dimensional coordinate system.
 * Provides methods to access the x and y coordinates and a string representation.
 */
public interface ILocation {

    /**
     * Gets the x-coordinate of this location.
     * @return the x-coordinate as an integer
     */
    public int getX();
    
    /**
     * Gets the y-coordinate of this location.
     * @return the y-coordinate as an integer
     */
    public int getY();
    
    /**
     * Returns a string representation of this location.
     * @return a string in the format "(x,y)"
     */
    public String toString();
    
}

