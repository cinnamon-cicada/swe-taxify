package sprint5;

import java.time.LocalDate;

/**
 * Interface representing a Driver profile.
 * Provides method signatures for accessing and modifying driver attributes.
 */
public interface IDriver {
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