package sprint3;

public class Shuttle extends Vehicle {

    public Shuttle(int id, ILocation location, ITaxiCompany company) {
        super(id, location);
        this.setCompany(company);
    }

    
    @Override
    public double calculateCost() {
        return super.calculateCost() * 1.5;
    }
}
