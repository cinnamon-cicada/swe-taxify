package sprint5;

/**
 * Interface defining the core operations of a taxi company.
 */
public interface ITaxiCompany {

    /**
     * @return company name
     */
    String getName();

    /**
     * @return total services
     */
    int getTotalServices();

    /**
     * Assigns a service to a user if possible.
     * @param user user ID
     * @param pinkRide require female driver
     * @param rideMode ride type
     * @return true if assigned
     */
    boolean provideService(int user, boolean pinkRide, String rideMode);

    /**
     * Assigns a rental service to a user if possible.
     * @param user user ID
     * @param vehicleType type of rental: SCOOTER or BIKE
     * @return true if assigned
     */
    boolean provideRentalService(int user, RentalVehicleType vehicleType);

    /**
     * Called when vehicle reaches pickup.
     * @param vehicle vehicle
     */
    void arrivedAtPickupLocation(IVehicle vehicle);

    /**
     * Called when vehicle reaches drop-off.
     * @param vehicle vehicle
     */
    void arrivedAtDropoffLocation(IVehicle vehicle);

    /**
     * Adds an observer.
     * @param observer observer
     */
    void addObserver(IObserver observer);

    /**
     * Notifies observer.
     * @param message message
     */
    void notifyObserver(String message);

    /**
     * Finds the nearest available rental vehicle of the specified type.
     * @param vehicleType type of rental: SCOOTER or BIKE
     * @return the nearest available rental vehicle's index
     */
    int findNearestRental(RentalVehicleType vehicleType);
}