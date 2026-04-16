package sprint3;

public class Taxi extends Vehicle {

    public Taxi(int id, ILocation location, ITaxiCompany company) {
        super(id, location);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() {
        return super.calculateCost() * 2;
    }
}
