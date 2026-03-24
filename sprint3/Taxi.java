package sprint3;

public class Taxi extends Vehicle {

    public Taxi(ILocation location, ITaxiCompany company) {
        super(0, location);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * 2;
    }
    
}
