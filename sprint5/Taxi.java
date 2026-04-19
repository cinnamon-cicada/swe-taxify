package sprint5;

public class Taxi extends Vehicle {

    public Taxi(int id, ILocation location, ITaxiCompany company, IDriver driver) {
        super(id, location, driver);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * 2;
    }
}
