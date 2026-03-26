package sprint3;

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

    /**
     * Returns the vehicle's unique identifier.
     * @return the vehicle ID
     */
    @Override
    public int getId() {
        return this.id;
    }
 
    /**
     * Returns the vehicle's current location.
     * @return the current location
     */
    @Override
    public ILocation getLocation() {
        return this.location; 
    }

    /**
     * Returns the vehicle's current destination.
     * @return the destination location
     */
    @Override
    public ILocation getDestination() {
        return this.destination;
    }
    
    /**
     * Returns the service currently assigned to this vehicle.
     * @return the current service, or null if no service is assigned
     */
    @Override
    public IService getService() {
        return this.service;
    }
    
    /**
     * Returns the vehicle's statistics.
     * @return the vehicle statistics
     */
    @Override
    public IStatistics getStatistics() {
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
        
    /**
     * Checks whether the vehicle is currently free and available for service.
     * @return true if the vehicle is free, false otherwise
     */
    @Override
    public boolean isFree() {
        if(this.status == VehicleStatus.FREE) {
            return true;
        }
        return false;
    }   
    
    @Override
    public void move() {
        // check if the route has locations to move to
        if (this.route.hasLocations()) {
            // get the next location from the driving route
            this.location = this.route.getNextLocation();
        }
        
        // if the route has no more locations, the vehicle has arrived at a destination
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

    /**
     * Calculates the cost for the current service based on distance.
     * This is a basic implementation that can be overridden by specific vehicle types.
     * @return the calculated service cost
     */
    @Override
    public double calculateCost() {
        return this.service.calculateDistance();
    }

    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
               " to pickup user " + this.service.getUser().getId() : " in service "));
    }    
}

