package sprint4;

/**
 * Interface representing a Driver profile.
 * Provides method signatures for accessing and modifying driver attributes.
 */
public interface IDriver {

    /**
     * Gets the driver's name.
     * @return the name of the driver
     */
    String getName();

    /**
     * Sets the driver's name.
     * @param name the name to set
     */
    void setName(String name);

    /**
     * Gets the driver's gender.
     * @return the gender of the driver
     */
    String getGender();

    /**
     * Sets the driver's gender.
     * @param gender the gender to set
     */
    void setGender(String gender);

    /**
     * Gets the driver's birth date.
     * @return the birth date of the driver
     */
    String getBirthDate();

    /**
     * Sets the driver's birth date.
     * @param birthDate the birth date to set
     */
    void setBirthDate(String birthDate);

    /**
     * Gets the driver's years of experience.
     * @return years of driving experience
     */
    int getYearsOfExperience();

    /**
     * Sets the driver's years of experience.
     * @param yearsOfExperience years of experience to set
     */
    void setYearsOfExperience(int yearsOfExperience);

    /**
     * Gets the driver's rating.
     * @return the driver's rating
     */
    double getRating();

    /**
     * Sets the driver's rating.
     * @param rating the rating to set
     */
    void setRating(double rating);
}