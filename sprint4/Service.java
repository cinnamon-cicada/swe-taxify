package sprint4;

import java.util.ArrayList;

/**
 * Represents a taxi service request, including user, pickup and dropoff locations, rating, and distance calculation.
 * Implements the IService interface to provide service management functionality.
 */
public class Service implements IService {
    /** The users associated with this service */
    private ArrayList<IUser> users;
    /** The pickup location for this service */
    private ILocation pickup;
    /** The dropoff location for this service */
    private ILocation dropoff;
    /** The star rating for this service */
    private int stars;
    /** The driver assigned to this service */
    private IDriver driver;
    /** Pink rides */
    private boolean isPink;
    /** Noise level: Silent or Standard */
    private String rideMode;



    /**
     * Constructs a Service with the specified user, pickup, and dropoff locations.
     * @param user the user requesting the service
     * @param pickup the pickup location
     * @param dropoff the dropoff location
     */
    public Service(IUser user, ILocation pickup, ILocation dropoff, boolean pinkRide, String rideMode) {
        this.users = new ArrayList<>();
        this.users.add(user);
        this.pickup = pickup;
        this.dropoff = dropoff; 
        this.stars = 0;
        this.isPink = pinkRide;
        this.rideMode = rideMode;
    }
    
    /**
     * Gets the users associated with this service.
     * @return the IUsers who are included in the service
     */
    @Override
    public ArrayList<IUser> getUsers() {
        return this.users;
    }
    
    /**
     * Gets the pickup location for this service.
     * @return the ILocation where the service starts
     */
    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }
    
    /**
     * Gets the dropoff location for this service.
     * @return the ILocation where the service ends
     */
    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }
    
    /**
     * Gets the star rating for this service.
     * @return the number of stars (typically 0-5)
     */
    @Override
    public int getStars() {
        return this.stars;
    }
    
    /**
     * Sets the star rating for this service.
     * @param stars the number of stars to set (typically 0-5)
     */
    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }
    
    /**
     * Calculates the distance between pickup and dropoff locations.
     * @return the distance in service units
     */
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() - this.dropoff.getY());
    }
    
    /**
     * Returns a string representation of this service.
     * @return a string describing the service details
     */
    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }

    /**
     * Add a user to the current service.
     */
    @Override
    public void addUser(IUser user) {
        this.users.add(user);
    }
}
