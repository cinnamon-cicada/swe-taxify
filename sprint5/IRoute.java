package sprint5;

/**
 * Represents a route consisting of a sequence of locations.
 * Provides methods to check for available locations, retrieve the next location, and generate a string representation.
 */
public interface IRoute {

    /**
     * Checks if the route contains any locations.
     * @return true if the route has locations, false otherwise
     */
    public boolean hasLocations();
    
    /**
     * Retrieves and removes the next location in the route.
     * @return the next ILocation in the route, or null if no locations remain
     */
    public ILocation getNextLocation();
    
    /**
     * Returns a string representation of the route.
     * @return a string describing the route's locations
     */
    public String toString();

}
