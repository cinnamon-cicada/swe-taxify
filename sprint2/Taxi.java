package sprint2;

public class Taxi extends Vehicle {

    public Taxi(ILocation location, ITaxiCompany company) {
        super(location);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * 2;
    }
    
}
