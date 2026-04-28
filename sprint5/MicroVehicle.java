package sprint5;

public class MicroVehicle implements IVehicle {
    private int id;
    private ITaxiCompany company;
    private IService service;
    private MicroVehicleStatus status;
    private ILocation location;
    private ILocation destination;
    private IStatistics statistics;
    private IRoute route;
    private IDriver driver;

    /**
     * Constructs a MicroVehicle with the given ID, location, and driver.
     * @param id the vehicle ID
     * @param location the initial location
     * @param driver the assigned driver
     */
    public MicroVehicle(int id, ILocation location, IDriver driver) {
        super(id, location, driver);
    }

    /**
     * Initializes all private member variables for a vehicle.
     * @param id the vehicle ID
     * @param location the initial location
     * @param driver the assigned driver
     */
    public Vehicle(int id, ILocation location, IDriver driver) {      //implementing all private membervariables   
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
        this.driver = driver;
    }

    /**
     * Sets the driver for this vehicle.
     * @param driver the driver to assign
     */
    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    };

    /**
     * Returns the driver assigned to this vehicle.
     * @return the vehicle's driver
     */
    @Override
    public IDriver getDriver() {
        return this.driver;
    };

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
    
    /**
     * Sets the company that operates this vehicle.
     * @param company the taxi company
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    /**
     * Picks up a service and sets destination to the service's pickup location.
     * @param service the service to pick up
     */
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"
        
        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = new Route(this.location, this.destination);        
        this.status = VehicleStatus.PICKUP;
    }

    /**
     * Starts the service and sets destination to the drop-off location.
     */
    @Override
    public void startService() {
        // set destination to the service drop-off location, 
        // and status to "service"
        this.destination = this.service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = MicroVehicleStatus.RIDING;
    }

    /**
     * Ends the current service and updates vehicle statistics.
     * @param riders the number of riders
     */
    @Override
    public void endService(int riders) {
        // update vehicle statistics
        double discountPrice = 0.6;
        if(riders == 1) {
            this.statistics.updateBilling(this.calculateCost());
        } else {
            this.statistics.updateBilling(this.calculateCost() * discountPrice * riders);
        }
        
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
        this.status = MicroVehicleStatus.FREE;
    }

    /**
     * Notifies the company when the vehicle arrives at the pickup location.
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location
        if(this.location.getX() == this.service.getPickupLocation().getX() && 
            this.location.getY() == this.service.getPickupLocation().getY()) {
            this.company.arrivedAtPickupLocation(this);
        }
    }
        
    /**
     * Notifies the company when the vehicle arrives at the drop-off location.
     */
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
        if(this.status == MicroVehicleStatus.FREE) {
            return true;
        }
        return false;
    }   
    
    /**
     * Moves the vehicle along its route or creates a random route if none exists.
     */
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
        return 1 + this.service.calculateDistance();
    }

    /**
     * Returns a string representation of the vehicle with its current status and location.
     * @return a string describing the vehicle's state
     */
    @Override
    public String toString() {
        String users = "";
        for(IUser user : this.service.getUsers()) {
            users += user + ", ";
        }
        users = users.substring(0, users.length() - 2);

        String result = this.id + " at " + this.location + " driving to " + this.destination +
                ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
                " to pickup user(s) " + users : " in service "));
        

        return result;
    }
}