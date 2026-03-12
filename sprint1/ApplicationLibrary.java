package sprint1;

import java.util.Random;

/**
 * Utility class providing helper methods for the taxi application including random number generation,
 * distance calculations, and location management on a 10x10 map grid.
 */
public class ApplicationLibrary {
    /** Minimum distance required between pickup and dropoff locations */
    public static final int MINIMUM_DISTANCE = 3;
    /** Width of the map grid in coordinate units */
    private static final int MAP_WIDTH = 10;
    /** Height of the map grid in coordinate units */
    private static final int MAP_HEIGHT = 10;    

    /**
     * Generates a random integer value.
     * @return a random integer between 0 and 9766
     */
    public static int rand() {
        Random random = new Random();
        
        return random.nextInt(9767);
    }
    
    /**
     * Generates a random integer value bounded by a maximum.
     * @param max the upper bound for the random number (exclusive)
     * @return a random integer between 0 and max-1
     */
    public static int rand(int max) {
        Random random = new Random();

        return random.nextInt(9767) % max;
    }
    
    /**
     * Calculates the distance between two locations.
     * @param a the first location
     * @param b the second location
     * @return the distance (sum of absolute differences in coordinates)
     */
    public static int distance(ILocation a, ILocation b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
    
    /**
     * Generates a random location on the map grid.
     * @return a randomly generated Location within the 10x10 map bounds
     */
    public static ILocation randomLocation() {
        return new Location(rand(MAP_WIDTH), rand(MAP_HEIGHT));
    }
    
    /**
     * Generates a random destination location that is at least MINIMUM_DISTANCE away from the given location.
     * @param location the starting location
     * @return a Location that is sufficiently far from the starting location
     */
    public static ILocation randomLocation(ILocation location) {
        ILocation destination;
        
        do {
            
            destination = new Location(rand(MAP_WIDTH), rand(MAP_HEIGHT));
            
        } while (distance(location, destination) < MINIMUM_DISTANCE);  
            
        return destination;
    }
}
