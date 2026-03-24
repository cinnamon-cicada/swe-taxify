package sprint3;

public class Taxi extends Vehicle {

    public Taxi(ILocation location, ITaxiCompany company) {
        super(0, location);
        this.setCompany(company);
    }

    @Override
    public double calculateCost() { //Taxi is 2 Euro instead of standard 1 Euro from Vehicle
        return super.calculateCost() * 2;
    }
    //Only need to implement classes that change from vehicle, thus no others needed
}
