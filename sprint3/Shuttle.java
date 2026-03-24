package sprint3;

public class Shuttle extends Vehicle {

    /**
     * Constructs a new Shuttle with the specified location and taxi company.
     * @param location the initial location of the shuttle
     * @param company the taxi company this shuttle belongs to
     */
    public Shuttle(ILocation location, ITaxiCompany company) {
        super(0, location);
        this.setCompany(company);
    }

    /**
     * Calculates the cost for this shuttle, which is 1.5 times the base vehicle cost.
     * @return the calculated cost as a double
     */
    @Override
    public double calculateCost() {
        return super.calculateCost() * 1.5;
    }
    
}
