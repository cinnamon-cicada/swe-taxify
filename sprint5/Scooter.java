package sprint5;

public class Scooter extends Vehicle {
    private double battery;  // Battery level from 0 to 100 (percentage)

    public Scooter(int id, ILocation location, IDriver driver, ICompany company) {
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

    @Override
    public double calculateCost() {
        return (super.calculateCost() - 1) * .15 + 1; // Scooters have a base cost of 1, and then charge 15% of the distance cost above that
    }

    /**
     * Starts the service and sets destination to the drop-off location.
     */
    @Override
    public void startService() {
        if (this.battery <= 25) {
            // Automatically recharge if battery is low before starting service
            rechargeBattery();
        }
        // set destination to the service drop-off location, 
        // and status to "service"
        this.destination = this.service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    } 

    /**
     * Ends the current service and updates vehicle statistics.
     * @param riders the number of riders
     */
    @Override
    public void endService(int riders) {
        // call parent method
        super.endService(riders);
        // decrease battery level after a service
        this.battery -= 25;
    }

    /**
    * Charges the scooter's battery to full (100%).
    */
    public void chargeBattery() {
        this.battery = 100;
    }
}