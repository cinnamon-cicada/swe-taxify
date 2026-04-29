package sprint5;

import java.time.LocalDate;

/**
 * Abstract class representing a human being.
 * Provides methods to access and modify human information, manage services, and interact with the taxi company.
 */
public class Person {

    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthDate;

    /**
     * Gets the first name of the human.
     * @return the first name as a string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the human.
     * @return the last name as a string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the human's name.
     * @return the name of the human
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Sets the human's name.
     * @param name the name to set
     */
    public void setName(String name) {
        String[] names = name.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
    }

    /**
     * Gets the gender of the human.
     * @return the gender as a character ('M' or 'F')
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets the gender of the human.
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Gets the birth date of the human.
     * @return the birth date as a LocalDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Set birth date of the human.
     * @param birthDate the birth date to set
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}