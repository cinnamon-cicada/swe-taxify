package sprint5;

import java.time.LocalDate;

/**
 * Concrete implementation of the IDriver interface.
 * Represents a driver with profile details such as name, gender,
 * birth date, experience, and rating.
 */
public class Driver extends Person implements IDriver {

    // driver’s profile
    private String name;
    private char gender;
    private LocalDate birthDate;
    private int yearsOfExperience;
    private double rating;

    /**
     * Default constructor.
     * Initializes a driver with default values.
     */
    public Driver() {
        super("Miss", "Driver", 'F', LocalDate.now());
        this.yearsOfExperience = 0;
        this.rating = 0.0;
    }

    /**
     * Parameterized constructor.
     * Initializes a driver with all attributes.
     *
     * @param name the driver's name
     * @param gender the driver's gender
     * @param birthDate the driver's birth date
     * @param yearsOfExperience years of driving experience
     * @param rating the driver's rating
     */
    public Driver(String name, char gender, LocalDate birthDate,
                  int yearsOfExperience, double rating) {
        super(name.split(" ")[0], name.split(" ")[1], gender, birthDate);
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }
}