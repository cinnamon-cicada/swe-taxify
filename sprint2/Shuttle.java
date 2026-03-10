package sprint2;

public class Shuttle extends Vehicle {

    public Shuttle(ILocation location, ITaxiCompany company) {
        super(location);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * 1.5;
    }
    
}
