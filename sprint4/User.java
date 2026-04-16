package sprint3;

import java.time.LocalDate;

/**
 * Represents a user in the taxi system with personal information and service management capabilities.
 * Implements the IUser interface to provide user-related functionality.
 */
public class User implements IUser {
    /** The unique identifier of the user */
    private int id;
    /** The first name of the user */
    private String firstName;
    /** The last name of the user */
    private String lastName;
    /** The gender of the user */
    private char gender;
    /** The birth date of the user */
    private LocalDate birthDate;
    /** The taxi company associated with this user */
    private ITaxiCompany company;
    /** Indicates if the user is currently in service */
    private boolean service;
    
    /**
     * Constructs a User with the specified details.
     * @param id the unique identifier
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender ('M' or 'F')
     * @param birthDate the birth date
     */
    public User(int id, String firstName, String lastName, char gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.service = false;
    }
    
    /**
     * Gets the unique identifier of the user.
     * @return the user ID as an integer
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Gets the first name of the user.
     * @return the first name as a string
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Gets the last name of the user.
     * @return the last name as a string
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Gets the gender of the user.
     * @return the gender as a character ('M' or 'F')
     */
    @Override
    public char getGender() {
       return this.gender;
    }

    /**
     * Gets the birth date of the user.
     * @return the birth date as a LocalDate
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Checks if the user is currently in service.
     * @return true if the user is in service, false otherwise
     */
    @Override
    public boolean getService() {
        return this.service;
    }
    
    /**
     * Sets the service status of the user.
     * @param service true to set the user in service, false otherwise
     */
    @Override
    public void setService(boolean service) {
        this.service = service;
    }
    
    /**
     * Sets the taxi company associated with this user.
     * @param company the ITaxiCompany to associate with the user
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    /**
     * Requests a service from the associated taxi company.
     * @param pinkRide requests a ride by, and for, women
     * @param rideMode "Silent" or "Standard" noise levels for the ride
     */
    @Override
    public void requestService(boolean pinkRide, String rideMode) {
        if (pinkRide) {
            int age = Period.between(this.getBirthDate(), LocalDate.now()).getYears();

            if (!this.getGender().equals("F") || age >= 18) {
                throw new IllegalArgumentException("Pink ride is only available for adult female drivers.");
            }
        }

        this.company.provideService(this.id, pinkRide, rideMode);
    }
    
    /**
     * Rates a completed service.
     * @param service the IService to rate
     */
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)
        
        if (ApplicationLibrary.rand() % 2 == 0) {
            service.setStars(ApplicationLibrary.rand(5) + 1);
        }
    }
    
    /**
     * Returns a string representation of the user.
     * @return a string describing the user's details
     */
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " + this.getLastName());
    }
}
