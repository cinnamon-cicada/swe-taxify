package sprint3;

/**
 * Interface representing a taxi company that manages services and provides basic operations.
 * Provides methods to get the company name, total services, and to provide services to users.
 */
public interface ITaxiCompany {

    /**
     * Gets the name of the taxi company.
     * @return the company name as a string
     */
    public String getName();    
    
    /**
     * Gets the total number of services provided by the company.
     * @return the total number of services
     */
    public int getTotalServices();
    
    /**
     * Attempts to provide a service to the specified user.
     * @param user the ID of the user requesting the service
     * @return true if the service was successfully provided, false otherwise
     */
    public boolean provideService(int user);

    /**
     * Notifies the company that a vehicle has arrived at the pickup location.
     * @param vehicle the vehicle in question
     */
    public void arrivedAtPickupLocation(IVehicle vehicle);

    /**
     * Notifies the company that a vehicle has arrived at the dropoff location.
     * @param vehicle the vehicle in question
     */
    public void arrivedAtDropoffLocation(IVehicle vehicle);
    
}