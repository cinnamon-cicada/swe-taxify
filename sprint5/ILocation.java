package sprint5;

/**
 * Represents a location in 2D.
 * Provides methods to access the x and y coordinates and a string representation.
 */
public interface ILocation {

    /**
     * Gets the x-coordinate.
     * @return the x-coordinate as an integer
     */
    public int getX();
    
    /**
     * Gets the y-coordinate.
     * @return the y-coordinate as an integer
     */
    public int getY();
    
    /**
     * Returns a string representation of this location.
     * @return a string in the format "(x,y)"
     */
    public String toString();
    
}

