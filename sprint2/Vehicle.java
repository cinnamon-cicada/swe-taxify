package sprint2;

public abstract class Vehicle implements IVehicle {
    private int id;
    private ITaxiCompany company;
    private IService service;
    private VehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private IRoute route;
        
    public Vehicle(int id, ILocation location) {      //implementing all private membervariables   
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
    }

    @Override
    public int getId() { //returns ID
        return this.id;
    }
 
    @Override
    public ILocation getLocation() { //returns location
        return this.location; 
    }

    @Override
    public ILocation getDestination() { //returns destination
        return this.destination;
    }
    
    @Override
    public IService getService() { //returns service
        return this.service;
    }
    
    @Override
    public IStatistics getStatistics() { //returns statistics
        return this.statistics; 
    }
    
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"
        
        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = new Route(this.location, this.destination);        
        this.status = VehicleStatus.PICKUP;
    }

    @Override
    public void startService() {
        // set destination to the service drop-off location, 
        // and status to "service"
        this.destination = this.service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

    @Override
    public void endService() {
        // update vehicle statistics
        
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();
        
        // if the service is rated by the user, update statistics
        
        if (this.service.getStars() != 0) {
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }
        
        // set service to null, and status to "free"
        
        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    }

    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location
        if(this.location.getX() == this.service.getPickupLocation().getX() && 
            this.location.getY() == this.service.getPickupLocation().getY()) {
            this.company.arrivedAtPickupLocation(this);
        }
    }
        
    @Override
    public void notifyArrivalAtDropoffLocation() {
        // notify the company that the vehicle is at the dropoff location
        if(this.location.getX() == this.service.getDropoffLocation().getX() && 
            this.location.getY() == this.service.getDropoffLocation().getY()) {
            this.company.arrivedAtDropoffLocation(this);
        }
     }
        
    @Override
    public boolean isFree() {  //checking if vehicle is free or not
        if(this.status == VehicleStatus.FREE) {
            return true;
        }
        return false;
    }   
    
    @Override
    public void move() {
        // get the next location from the driving route
        
        this.location = this.route.getNextLocation();        
        
        // if the route has more locations the vehicle continues its route, otherwise the vehicle has arrived to a pickup or drop off location
        
        if (!this.route.hasLocations()) {
            if (this.service == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = new Route(this.location, this.destination);
            }
            else {
                // check if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.service.getPickupLocation();
                ILocation destination = this.service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

                }        
            }
        }
    }

    @Override
    public double calculateCost() { //basic cost calculator that will be overriden by specific vehicles later
        return this.service.calculateDistance();
    }

    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
               " to pickup user " + this.service.getUser().getId() : " in service "));
    }    
}

