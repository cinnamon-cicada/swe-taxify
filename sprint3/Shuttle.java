package sprint3;

public class Shuttle extends Vehicle {

    public Shuttle(ILocation location, ITaxiCompany company) {
        super(0, location);
        this.setCompany(company);
    }

    
    @Override
    public double calculateCost() {//Shuttle is 1 Euro instead of standard 1 Euro from Vehicle
        return super.calculateCost() * 1.5;
    }
    //Only need to implement classes that change from vehicle, thus no others needed
}
