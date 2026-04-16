package sprint3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a route consisting of a sequence of locations from a starting point to a destination.
 * Implements the IRoute interface to provide route management functionality.
 */
public class Route implements IRoute {
    /** The list of locations that make up the route */
    private List<ILocation> route;
    
    /**
     * Constructs a Route from the specified location to the destination.
     * The route is calculated as a sequence of locations moving first in the x-direction, then y-direction.
     * @param location the starting location
     * @param destination the ending location
     */
    public Route(ILocation location, ILocation destination) {
        this.route = setRoute(location, destination);
    }
    
    /**
     * Checks if the route contains any locations.
     * @return true if the route has locations, false otherwise
     */
    @Override
    public boolean hasLocations() {
        return !this.route.isEmpty();
    }
            
    /**
     * Retrieves and removes the next location in the route.
     * @return the next ILocation in the route, or null if no locations remain
     */
    @Override
    public ILocation getNextLocation() {
        ILocation location = this.route.get(0);
        
        this.route.remove(0);
        
        return location;
    }
    
    /**
     * Returns a string representation of the route.
     * @return a string describing the route's locations
     */
    @Override
    public String toString() {
        String route = "";
           
           for (ILocation location : this.route) {
               route = route + location.toString() + " ";
           }
       
           return route;        
    }
    
    /**
     * Calculates and sets the route from the starting location to the destination.
     * The route moves first in the x-direction, then in the y-direction.
     * @param location the starting location
     * @param destination the ending location
     * @return a list of ILocation objects representing the route
     */
    private static List<ILocation> setRoute(ILocation location, ILocation destination) {
        List<ILocation> route = new ArrayList<ILocation>();
        
        int x1 = location.getX();
        int y1 = location.getY();
        
        int x2 = destination.getX();
        int y2 = destination.getY();
        
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
       
        for (int i=1; i<=dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }
        
        for (int i=1; i<=dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;
            
            route.add(new Location(x1, y1));
        }
        
        return route;
    }       
}
