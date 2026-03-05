package sprint2;

/**
 * Represents a taxi service request, including user, pickup and dropoff locations, rating, and distance calculation.
 * Implements the IService interface to provide service management functionality.
 */
public class Service implements IService {
    /** The user associated with this service */
    private IUser user;
    /** The pickup location for this service */
    private ILocation pickup;
    /** The dropoff location for this service */
    private ILocation dropoff;
    /** The star rating for this service */
    private int stars;
    
    /**
     * Constructs a Service with the specified user, pickup, and dropoff locations.
     * @param user the user requesting the service
     * @param pickup the pickup location
     * @param dropoff the dropoff location
     */
    public Service(IUser user, ILocation pickup, ILocation dropoff) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff; 
        this.stars = 0;
    }
    
    /**
     * Gets the user associated with this service.
     * @return the IUser who requested the service
     */
    @Override
    public IUser getUser() {
        return this.user;
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
}
