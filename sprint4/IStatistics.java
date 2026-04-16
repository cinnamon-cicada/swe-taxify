package sprint3;

/**
 * Interface for tracking and managing statistics related to taxi services.
 * Provides methods to retrieve cumulative statistics and update counters.
 */
public interface IStatistics {

    /**
     * Gets the total number of services provided.
     * @return the number of services
     */
    public int getServices();
    
    /**
     * Gets the total number of reviews received.
     * @return the number of reviews
     */
    public int getReviews();
    
    /**
     * Gets the average star rating.
     * @return the average rating as a double
     */
    public double getStars();
    
    /**
     * Gets the total distance traveled across all services.
     * @return the total distance in service units
     */
    public int getDistance();
    
    /**
     * Gets the total billing amount accumulated.
     * @return the total billing amount
     */
    public double getBilling();
    
    /**
     * Increments the service count by one.
     */
    public void updateServices();
    
    /**
     * Increments the review count by one.
     */
    public void updateReviews();
    
    /**
     * Adds stars to the cumulative star total.
     * @param stars the number of stars to add (typically 1-5)
     */
    public void updateStars(int stars);
    
    /**
     * Adds distance to the cumulative total distance.
     * @param distance the distance to add in service units
     */
    public void updateDistance(int distance);
    
    /**
     * Adds to the cumulative billing amount.
     * @param billing the billing amount to add
     */
    public void updateBilling(double billing);
    
}