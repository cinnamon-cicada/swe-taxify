package sprint3;

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
     */
    public void requestService();
    
    /**
     * Gets the total number of services completed.
     * @return the total service count
     */
    public int getTotalServices();
    
}