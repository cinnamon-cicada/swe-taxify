package sprint5;

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
     * @param pinkRide if it's a ride offered by a woman driver to women/kids
     * @param rideMode Silent or Standard offered
     * @return true if the service was successfully provided, false otherwise
     */
    public boolean provideService(int user, boolean pinkRide, String rideMode);

    /**
     * Assigns a service to a user if possible.
     * @param user user ID
     * @param vehicleType type of rental: SCOOTER or BIKE
     * @return true if assigned
     */
    public boolean provideRentalService(int user, RentalVehicleType vehicleType);

    /**
     * Finds the nearest available rental vehicle of the specified type.
     * @param vehicleType type of rental: SCOOTER or BIKE
     * @return the nearest available MicroVehicle of the specified type, or null if none are
     */
    public int findNearestRental(RentalVehicleType vehicleType);

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