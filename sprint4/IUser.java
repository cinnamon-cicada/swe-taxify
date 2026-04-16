package sprint3;

import java.time.LocalDate;

/**
 * Interface representing a user.
 * Provides methods to access and modify user information, manage services, and interact with the taxi company.
 */
public interface IUser {

    /**
     * Gets the unique identifier of the user.
     * @return the user ID as an integer
     */
    public int getId();
    
    /**
     * Gets the first name of the user.
     * @return the first name as a string
     */
    public String getFirstName();
    
    /**
     * Gets the last name of the user.
     * @return the last name as a string
     */
    public String getLastName();
    
    /**
     * Gets the gender of the user.
     * @return the gender as a character ('M' or 'F')
     */
    public char getGender();
    
    /**
     * Gets the birth date of the user.
     * @return the birth date as a LocalDate
     */
    public LocalDate getBirthDate();
    
    /**
     * Checks if the user is currently in service.
     * @return true if the user is in service, false otherwise
     */
    public boolean getService();
    
    /**
     * Sets the service status of the user.
     * @param service true to set the user in service, false otherwise
     */
    public void setService(boolean service);
    
    /**
     * Sets the taxi company associated with this user.
     * @param company the ITaxiCompany to associate with the user
     */
    public void setCompany(ITaxiCompany company);
    
    /**
     * Requests a service from the associated taxi company.
     */
    public void requestService(boolean pinkRide, String rideMode);
    
    /**
     * Rates a completed service.
     * @param service the IService to rate
     */
    public void rateService(IService service);
    
    /**
     * Returns a string representation of the user.
     * @return a string describing the user's details
     */
    public String toString();

}