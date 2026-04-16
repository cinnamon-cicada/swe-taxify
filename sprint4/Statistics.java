package sprint3;

/**
 * Tracks and manages statistics for taxi services including service count, ratings, distance traveled, and billing.
 * This class implements the IStatistics interface and maintains cumulative data about service usage.
 */
public class Statistics implements IStatistics {
    /** Total number of services provided */
    private int services;
    /** Total number of ratings/reviews received */
    private int ratings;
    /** Cumulative star rating sum */
    private int stars;
    /** Total distance traveled in service units */
    private int distance;
    /** Total billing amount in currency units */
    private double billing;
    
    /**
     * Constructs a Statistics object with all counters initialized to zero.
     */
    public Statistics() {
        this.services = 0;
        this.ratings = 0;
        this.stars = 0;
        this.distance = 0;
        this.billing = 0;
    }
    
    /**
     * Gets the total number of services provided.
     * @return the number of services
     */
    @Override
    public int getServices() {
        return this.services;
    }
    
    /**
     * Gets the total number of reviews received.
     * @return the number of reviews
     */
    @Override
    public int getReviews() {
        return this.ratings;
    }
    
    /**
     * Calculates the average star rating rounded to two decimal places.
     * @return the average rating as a double, or 0 if no reviews exist
     */
    @Override
    public double getStars() {
        double stars = (double) this.stars / (double) this.ratings;
        
        return Math.round(stars*100.0)/100.0;
    }
    
    /**
     * Gets the total distance traveled across all services.
     * @return the total distance in service units
     */
    @Override
    public int getDistance() {
        return this.distance;
    }
    
    /**
     * Gets the total billing amount accumulated.
     * @return the total billing amount
     */
    @Override
    public double getBilling() {
        return this.billing;
    }
    
    /**
     * Increments the service count by one.
     */
    @Override
    public void updateServices() {
        this.services++;
    }
    
    /**
     * Increments the review count by one.
     */
    @Override
    public void updateReviews() {
        this.ratings++;
    }
    
    /**
     * Adds stars to the cumulative star total.
     * @param stars the number of stars to add (typically 1-5)
     */
    @Override
    public void updateStars(int stars) {
        this.stars = this.stars + stars;
    }
    
    /**
     * Adds distance to the cumulative total distance.
     * @param distance the distance to add in service units
     */
    @Override
    public void updateDistance(int distance) {
        this.distance = this.distance + distance;
    }
    
    /**
     * Adds to the cumulative billing amount.
     * @param billing the billing amount to add
     */
    @Override
    public void updateBilling(double billing) {
        this.billing = this.billing + billing;
    }    
}
