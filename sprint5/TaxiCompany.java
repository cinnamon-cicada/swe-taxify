package sprint5;

import java.util.List;
import java.util.Random;

/** Core taxi service provider. */
public class TaxiCompany implements ITaxiCompany, ISubject {

    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;

    /**
     * Initializes company and links users and vehicles.
     * @param name company name
     * @param users users list
     * @param vehicles vehicles list
     */
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;

        for (IUser user : this.users) {
            user.setCompany(this);
        }

        for (IVehicle vehicle : this.vehicles) {
            vehicle.setCompany(this);
        }
    }

    /** @return company name */
    @Override
    public String getName() {
        return this.name;
    }

    /** @return total services */
    @Override
    public int getTotalServices() {
        return this.totalServices;
    }

    /**
     * Assigns a service to a user if possible.
     * @param user user ID
     * @param pinkRide require female driver
     * @param rideMode ride type
     * @return true if assigned
     */
    @Override
    public boolean provideService(int user, boolean pinkRide, String rideMode) {
        int userIndex = findUserIndex(user);
        int[] vehicleData = findFreeVehicle(pinkRide);
        int vehicleIndex = vehicleData[0];

        boolean sharedRide = vehicleData[1] == 1;
        boolean noVehicle = vehicleData[1] == -1;
        boolean accept = false;

        if (sharedRide || noVehicle) {
            while(!accept && sharedRide || noVehicle) {
                int[] tempVehicle = findFreeVehicle(pinkRide);
                vehicleIndex = tempVehicle[0];
                sharedRide = tempVehicle[1] == 1;
                noVehicle = tempVehicle[1] == -1;
                accept = this.users.get(userIndex).acceptSharedRide();

                // obtain consent of other passenger(s)
                for(IUser u : this.vehicles.get(vehicleIndex).getService().getUsers()) {
                    accept = accept && u.acceptSharedRide();
                }
            }
        }

        if (vehicleIndex >= 0) {

            ILocation origin, destination;

            do {
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) 
                     < ApplicationLibrary.MINIMUM_DISTANCE);

            this.users.get(userIndex).setService(true);

            IService service;
            if(sharedRide) {
                service = this.vehicles.get(vehicleIndex).getService();
                service.addUser(this.users.get(userIndex));
            } else {
                service = new Service(this.users.get(userIndex), origin, destination, pinkRide, rideMode);
                this.vehicles.get(vehicleIndex).pickService(service);
            }

            notifyObserver(
                "User " + this.users.get(userIndex).getId() +
                " requests a " + rideMode + " service from " + service.toString() +
                ", the ride is assigned to " +
                this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " +
                this.vehicles.get(vehicleIndex).getId() + " at location " +
                this.vehicles.get(vehicleIndex).getLocation().toString()
            );

            this.totalServices++;

            return true;
        }

        return false;
    }

    /**
     * Assigns a service to a user if possible.
     * @param user user ID
     * @param vehicleType type of rental: Scooter, Bike
     * @return true if assigned
     */
    @Override
    public boolean provideRentalService(int user, String vehicleType) {
        int userIndex = findUserIndex(user);
        MicroVehicle rental = findNearestRental(vehicleType);
        int vehicleIndex = vehicleData[0];

        if (rental != null) {

            ILocation origin, destination;

            do {
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) 
                     < ApplicationLibrary.MINIMUM_DISTANCE);

            this.users.get(userIndex).setService(true);

            IService service;
            if(sharedRide) {
                service = this.vehicles.get(vehicleIndex).getService();
                service.addUser(this.users.get(userIndex));
            } else {
                service = new Service(this.users.get(userIndex), origin, destination, pinkRide, rideMode);
                this.vehicles.get(vehicleIndex).pickService(service);
            }

            notifyObserver(
                "User " + this.users.get(userIndex).getId() +
                " requests a Rental " + vehicleType + " service from " + service.toString() +
                ", the ride is assigned to " +
                this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " +
                this.vehicles.get(vehicleIndex).getId() + " at location " +
                this.vehicles.get(vehicleIndex).getLocation().toString()
            );

            this.totalServices++;

            return true;
        }

        return false;
    }

    /**
     * Called when vehicle reaches pickup.
     * @param vehicle vehicle
     */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        IService service = vehicle.getService();
        this.totalServices++;

        String note = String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " picks up user(s) ";
        for(IUser u : service.getUsers()) {
            note += u.getId() + ", ";
        }

        note = note.substring(0, note.length() - 2);
        notifyObserver(note);
        vehicle.startService();
    }

    /**
     * Called when vehicle reaches drop-off.
     * @param vehicle vehicle
     */
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {

        IService service = vehicle.getService(); 
        String note = String.format("%-8s", vehicle.getClass().getSimpleName()) +
            vehicle.getId() + " drops off user(s) ";

        for(IUser u : service.getUsers()) {
            int user = u.getId();
            int userIndex = findUserIndex(user);

            this.users.get(userIndex).rateService(service);
            this.users.get(userIndex).setService(false);

            note += user + ", ";
        }      

        this.totalServices--;    

        note = note.substring(0, note.length() - 2);
        notifyObserver(note);

        vehicle.endService(service.getUsers().size());
    }

    /**
     * Adds an observer.
     * @param observer observer
     */
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }

    /**
     * Notifies observer.
     * @param message message
     */
    @Override
    public void notifyObserver(String message) {
        if (this.observer == null) {
            this.observer = observer;
        }
        observer.updateObserver(message);
    }

    /**
     * Finds available vehicle.
     * @param pinkRide require female driver
     * @return [index, 0 free / 1 shared / -1 none]
     */
    private int[] findFreeVehicle(boolean pinkRide) {
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).isFree()) {
                if (!pinkRide || pinkRide && this.vehicles.get(i).getDriver().getGender() == 'F') {
                    return new int[]{i, 0};
                }
            } else if (this.vehicles.get(i).getService().getUsers().size() < 2) {
                return new int[]{i, 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Finds user index by ID.
     * @param id user ID
     * @return index or -1
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