package sprint5;

public class Bike extends MicroVehicle {
    private RentalVehicleType rentalType = RentalVehicleType.BIKE;

    /**
     * Constructs a Bike with the given ID, location, driver, and company.
     * @param id the vehicle ID
     * @param location the initial location
     * @param driver the assigned driver
     * @param company the taxi company this bike belongs to
     */
    public Bike(int id, ILocation location, Person driver, ITaxiCompany company) {
        super(id, location, driver);
        this.setCompany(company);
    }

    /**
     * Calculates the cost of a service for this bike.
     * @return the calculated cost
     */
    @Override
    public double calculateCost() {
        return (super.calculateCost() - 1) * .25 + 1; // Bikes have a base cost of 1, and then charge 25% of the distance cost above that   
    }

    /**
     * Get vehicle type
     */
    @Override
    public RentalVehicleType getRentalType() {
        return this.rentalType;
    }
}