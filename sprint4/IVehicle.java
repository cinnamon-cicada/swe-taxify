package sprint4;

public interface IVehicle extends IMovable {

    /**
     * Gets the unique identifier of this vehicle.
     * @return the vehicle's ID
     */
    public int getId();

    /**
     * Gets the current location of this vehicle.
     * @return the current location
     */
    public ILocation getLocation();

    /**
     * Gets the destination location of this vehicle.
     * @return the destination location
     */
    public ILocation getDestination();

    /**
     * Gets the current service assigned to this vehicle.
     * @return the current service
     */
    public IService getService();

    /**
     * Gets the statistics for this vehicle.
     * @return the vehicle's statistics
     */
    public IStatistics getStatistics();

    /**
     * Assigns a driver.
     *
     * @param driver the driver to set
     * @return the driver that was assigned
     */
    public void setDriver(IDriver driver);

    /**
     * Retrieves the current driver.
     *
     * @return the current driver, or null if no driver is assigned
     */
    public IDriver getDriver();

    /**
     * Sets the taxi company this vehicle belongs to.
     * @param company the taxi company
     */
    public void setCompany(ITaxiCompany company);

    /**
     * Assigns a service to this vehicle.
     * @param service the service to pick
     */
    public void pickService(IService service);

    /**
     * Starts the current service.
     */
    public void startService();

    /**
     * Ends the current service.
     * @param riders number of riders
     */
    public void endService(int riders);

    /**
     * Notifies that the vehicle has arrived at the pickup location.
     */
    public void notifyArrivalAtPickupLocation();

    /**
     * Notifies that the vehicle has arrived at the dropoff location.
     */
    public void notifyArrivalAtDropoffLocation();

    /**
     * Checks if this vehicle is free (not assigned to a service).
     * @return true if the vehicle is free, false otherwise
     */
    public boolean isFree();

    /**
     * Calculates the cost for the current service.
     * @return the calculated cost
     */
    public double calculateCost();

    /**
     * Returns a string representation of this vehicle.
     * @return a string representation
     */
    @Override
    public String toString();
    
}
