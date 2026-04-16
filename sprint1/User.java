package sprint1;

import java.time.LocalDate;

/**
 * Represents a user in the taxi system with personal information and service management capabilities.
 * Implements the IUser interface.
 */
public class User implements IUser {
    /** The unique identifier */
    private int id;
    /** The first name */
    private String firstName;
    /** The last name */
    private String lastName;
    /** The gender */
    private char gender;
    /** The birth date */
    private LocalDate birthDate;
    /** The taxi company associated with this user */
    private ITaxiCompany company;
    /** Indicates if the user is currently requesting a service */
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
     * Gets the unique identifier.
     * @return the user ID as an integer
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Gets the first name.
     * @return the first name as a string
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Gets the last name.
     * @return the last name as a string
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Gets the gender.
     * @return the gender as a character ('M' or 'F')
     */
    @Override
    public char getGender() {
       return this.gender;
    }

    /**
     * Gets the birth date.
     * @return the birth date as a LocalDate
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Checks if the user is currently requesting a service.
     * @return true if the user is requesting a service, false otherwise
     */
    @Override
    public boolean getService() {
        return this.service;
    }
    
    /**
     * Sets the service status.
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
     */
    @Override
    public void requestService() {
        this.company.provideService(this.id);
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
     * Returns a string representation.
     * @return a string describing the user's details
     */
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " + this.getLastName());
    }
}
