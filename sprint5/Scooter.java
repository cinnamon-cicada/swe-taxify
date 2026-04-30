package sprint5;

public class Scooter extends MicroVehicle {
    private double battery;  // Battery level from 0 to 100 (percentage)
    private RentalVehicleType rentalType = RentalVehicleType.SCOOTER;

    /**
     * Constructs a Scooter with the given ID, location, driver, and company.
     * @param id the vehicle ID
     * @param location the initial location
     * @param driver the assigned driver
     * @param company the taxi company this scooter belongs to
     */
    public Scooter(int id, ILocation location, Person driver, ITaxiCompany company) {
        super(id, location, driver);
        this.setCompany(company);
    }

    /**
     * Gets the battery level for this scooter.
     * @return the battery level (0-100)
     */
    public double getBattery() {
        return battery;
    }

    /**
     * Sets the battery level for this scooter.
     * @param battery the battery level to set (0-100)
     */
    @Override
    public double calculateCost() {
        return (super.calculateCost() - 1) * .15 + 1; // Scooters have a base cost of 1, and then charge 15% of the distance cost above that
    }

    /**
     * Start the service
     */
    @Override
    public void startService() {
        // set destination to the service drop-off location, 
        // and status to "service"
        if(this.battery <= 25) {
            this.getCompany().notifyObserver("Scooter " + this.getId() + " has insufficient battery to start service. Current battery: " + this.battery + "%");
            this.chargeBattery();
        }
        super.startService();
    }

    /**
     * Ends the current service and updates vehicle statistics.
     * @param riders the number of riders
     */
    @Override
    public void endService(int riders) {
        this.battery -= 25;
        this.getCompany().notifyObserver("Scooter " + this.getId() + " current battery: " + this.battery + "%");
        super.endService(riders);
    }

    /**
    * Charges the scooter's battery to full (100%).
    */
    public void chargeBattery() {
        this.getCompany().notifyObserver("Charging scooter " + this.getId() + " from " + this.battery + "% to 100%.");
        this.battery = 100;
    }

    /**
     * Get rental type
     */
    @Override
    public RentalVehicleType getRentalType() {
        return this.rentalType;
    }
}