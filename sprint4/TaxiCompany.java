package sprint3;

import java.util.List;

/**
 * TaxiCompany represents the core service provider in the simulation.
 * It manages users, vehicles, service assignment, and observer notifications.
 */
public class TaxiCompany implements ITaxiCompany, ISubject {

    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;

    /**
     * Constructor initializes the company with its name, users, and vehicles.
     * It also assigns this company instance to each user and vehicle.
     */
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;

        // Link each user to this company
        for (IUser user : this.users) {
            user.setCompany(this);
        }

        // Link each vehicle to this company
        for (IVehicle vehicle : this.vehicles) {
            vehicle.setCompany(this);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }

    @Override
    public boolean provideService(int user, boolean pinkRide, String rideMode) {
        int userIndex = findUserIndex(user);        
        int vehicleIndex = findFreeVehicle();

        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks

        if (vehicleIndex != -1) {
            ILocation origin, destination;

            // Generate valid pickup and drop-off locations
            do {
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) 
                     < ApplicationLibrary.MINIMUM_DISTANCE);

            // update the user status
            this.users.get(userIndex).setService(true);

            // create a service with the user, the pickup and the drop-off location
            IService service = new Service(this.users.get(userIndex), origin, destination, pinkRide, rideMode);

            // assign the new service to the vehicle
            this.vehicles.get(vehicleIndex).pickService(service);

            // Notify observer about the new service assignment
            notifyObserver(
                "User " + this.users.get(userIndex).getId() +
                " requests a " + rideMode + " service from " + service.toString() +
                ", the ride is assigned to " +
                this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " +
                this.vehicles.get(vehicleIndex).getId() + " at location " +
                this.vehicles.get(vehicleIndex).getLocation().toString()
            );

            // update the counter of services
            this.totalServices++;

            return true;
        }

        return false;
    }

    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        // notify the observer a vehicle arrived at the pickup location
        IService service = vehicle.getService();
        int user = service.getUser().getId();
        int userIndex = findUserIndex(user);
        this.totalServices++; // Increment total services when a pickup occurs

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " picks up user " + user);
        vehicle.startService();
    }

    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location

        IService service = vehicle.getService();       
        int user = service.getUser().getId();
        int userIndex = findUserIndex(user);

        // the taxi company requests the user to rate the service, and updates its status
        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services
        this.totalServices--;    

        notifyObserver(
            String.format("%-8s", vehicle.getClass().getSimpleName()) +
            vehicle.getId() + " drops off user " + user
        );

        vehicle.endService();
    }

    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver(String message) {
        // Initialize observer
        if (this.observer == null) {
            this.observer = observer;
        }

        // Notify
        observer.updateObserver(message);
    }

    /**
     * Finds the first available (free) vehicle.
     * @return index of the free vehicle, or -1 if none are available.
     */
    private int findFreeVehicle(boolean pinkRide) {
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).isFree() && (
                !pinkRide ||
                pinkRide && this.vehicles.get(i).getDriver().getGender() == "F")
                ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of a user by ID.
     * @return index of the user, or -1 if not found.
     */
    private int findUserIndex(int id) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}