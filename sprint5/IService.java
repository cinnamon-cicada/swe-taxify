package sprint5;

import java.util.ArrayList;

/**
 * Represents a taxi service, including user, pickup and dropoff locations, rating, and distance calculation.
 * Provides methods to access and modify service details.
 */
public interface IService {

    /**
     * Gets the users associated with this service.
     * @return the IUsers who requested the service
     */
    public ArrayList<IUser> getUsers();
    
    /**
     * Gets the pickup location for this service.
     * @return the ILocation where the service starts
     */
    public ILocation getPickupLocation();
    
    /**
     * Gets the dropoff location for this service.
     * @return the ILocation where the service ends
     */
    public ILocation getDropoffLocation();
    
    /**
     * Gets the star rating for this service.
     * @return the number of stars (typically 0-5)
     */
    public int getStars();
    
    /**
     * Sets the star rating for this service.
     * @param stars the number of stars to set (typically 0-5)
     */
    public void setStars(int stars);
    
    /**
     * Calculates the distance between pickup and dropoff locations.
     * @return the distance in service units
     */
    public int calculateDistance();
    
    /**
     * Returns a string representation of this service.
     * @return a string describing the service details
     */
    public String toString();

    /**
     * Add a user to the current service.
     */
    public void addUser(IUser user);
    
}