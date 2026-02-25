package sprint1;

public interface IRoute {

    public boolean hasLocations();
    public ILocation getNextLocation();
    public String toString();

}
