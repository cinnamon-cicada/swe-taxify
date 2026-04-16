package sprint4;

public interface IApplicationSimulator {
    /**
     * Displays the current status of the taxi application.
     */
    public void show();
    
    /**
     * Displays aggregated statistics for all vehicles.
     */
    public void showStatistics();
    
    /**
     * Updates the application state during each simulation iteration.
     */
    public void update();
    
    /**
     * Requests a service from the taxi company.
     * @param pinkRide if a ride is by women, for women/children
     * @param rideMode if a ride is Silent or Standard
     */
    public void requestService(boolean pinkRide, String rideMode);
    
    /**
     * Gets the total number of services completed.
     * @return the total service count
     */
    public int getTotalServices();
    
}