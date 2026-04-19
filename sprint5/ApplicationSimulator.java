package sprint5;

import java.util.List;

/**
 * ApplicationSimulator acts as the main orchestrator of the taxi company simulation.
 * It observes the company, updates vehicle movement, triggers service requests,
 * and displays system status and statistics.
 */
public class ApplicationSimulator implements IApplicationSimulator, IObserver {

    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;

    /**
     * Constructor initializes the simulator with a company, users, and vehicles.
     */
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
    }

    /**
     * Displays the current status of all vehicles in the simulation.
     * Useful for debugging or visualizing the system state.
     */
    @Override
    public void show() {
        System.out.println("\n" + this.company.getName() + " status \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    /**
     * Prints aggregated statistics for each vehicle:
     * - number of services completed
     * - total distance traveled
     * - total billing generated
     * - number of reviews and average stars
     */
    @Override
    public void showStatistics() {
        String s = "\n" + this.company.getName() + " statistics \n";

        for (IVehicle vehicle : this.vehicles) {
            s = s + "\n" +
                String.format("%-8s", vehicle.getClass().getSimpleName()) +
                String.format("%2s", vehicle.getId()) + " " +
                String.format("%2s", vehicle.getStatistics().getServices()) + " services " +
                String.format("%3s", vehicle.getStatistics().getDistance()) + " km. " +
                String.format("%3s", vehicle.getStatistics().getBilling()) + " eur. " +
                String.format("%2s", vehicle.getStatistics().getReviews()) + " reviews " +
                String.format("%-4s", vehicle.getStatistics().getStars()) + " stars";
        }

        System.out.println(s);
    }

    /**
     * Called when the simulation updates.
     * Moves every vehicle to its next location according to its internal logic.
     */
    @Override
    public void update() {
        for (IVehicle vehicle : this.vehicles) {
            vehicle.move();
        }
    }

    /**
     * Requests a service from the taxi company for the first available user.
     * A user is considered available if they are not currently in a service.
     * @param pinkRide if a ride is by women, for women/children
     * @param rideMode if a ride is Silent or Standard
     */
    @Override
    public void requestService(boolean pinkRide, String rideMode) {
        for (IUser user : this.users) {
            if (!user.getService()) { // user is free
                this.company.provideService(user.getId(), pinkRide, rideMode);
                break; // request only one service per update cycle
            }
        }
    }

    /**
     * Returns the total number of services completed by the company.
     */
    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }

    /**
     * Observer callback: prints any message sent by the observed subject.
     */
    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }
}