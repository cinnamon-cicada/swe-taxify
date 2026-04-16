package sprint4;

public class Shuttle extends Vehicle {

    public Shuttle(int id, ILocation location, ITaxiCompany company, IDriver driver) {
        super(id, location, driver);
        this.setCompany(company);
    }

    
    @Override
    public double calculateCost() {
        return super.calculateCost() * 1.5;
    }
}
