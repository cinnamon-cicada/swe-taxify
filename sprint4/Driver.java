package sprint4;

/**
 * Concrete implementation of the IDriver interface.
 * Represents a driver with profile details such as name, gender,
 * birth date, experience, and rating.
 */
public class Driver implements IDriver {

    // driver’s profile
    private String name;
    private String gender;
    private String birthDate;
    private int yearsOfExperience;
    private double rating;

    /**
     * Default constructor.
     * Initializes a driver with default values.
     */
    public Driver() {
        this.name = "";
        this.gender = "";
        this.birthDate = "";
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
    public Driver(String name, String gender, String birthDate,
                  int yearsOfExperience, double rating) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGender() {
        return gender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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