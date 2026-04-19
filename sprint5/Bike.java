package sprint5;

public class Bike extends MicroVehicle {
    public Bike(int id, ILocation location, IDriver driver) {
        super(id, location, driver);
    }

    @Override
    public double calculateCost() {
        return (super.calculateCost() - 1) * .25 + 1; // Bikes have a base cost of 1, and then charge 25% of the distance cost above that   
    }
}