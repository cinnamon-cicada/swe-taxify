package sprint4;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

// Declare a list of users. Instantiate at least 15 users.
// Declare a list of vehicles. Instantiate at least 10 vehicles (Taxis and Shuttles) and locate them at random locations of the city map.
// Instantiate the taxi company and the application simulator. Add the application simulator as an observer of the taxy company.
// The simulation begins with at least 5 requests of service, and ends when all vehicles end their services. Show the status of the application each iteration and update its state. Simulate a request of a service randomly, to avoid requesting a service each iteration.
// Finally, show the statistics.

public class TestProgram {
    public static void main(String[] args) {
        // Declare two drivers
        IDriver driverA = new Driver("DriverA", 'F', LocalDate.of(1990, 7, 19), 5, 4.8);
        IDriver driverB = new Driver("DriverB", 'M', LocalDate.of(1990, 7, 19), 5, 4.8);

        // Declare a list of users and instantiate at least 15 users
        List<IUser> users = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            char gender = (i % 2 == 0) ? 'M' : 'F';
            LocalDate birthDate = LocalDate.of(1990 + (i % 10), 1 + (i % 12), 1 + (i % 28));
            users.add(new User(i, "First" + i, "Last" + i, gender, birthDate));
        }

        // Declare a list of vehicles
        List<IVehicle> vehicles = new ArrayList<>();

        // Instantiate the taxi company (with empty vehicles list initially)
        TaxiCompany company = new TaxiCompany("TaxiCo", users, vehicles);

        // Instantiate at least 10 vehicles (Taxis and Shuttles) at random locations
        for (int i = 0; i < 10; i++) {
            ILocation location = ApplicationLibrary.randomLocation();
            IVehicle vehicle;
            if (i < 5) {
                vehicle = new Taxi(i, location, company, driverA);
            } else {
                vehicle = new Shuttle(i, location, company, driverB);
            }
            vehicles.add(vehicle);
        }

        // Instantiate the application simulator and add as observer
        IApplicationSimulator simulator = new ApplicationSimulator(company, users, vehicles);
        IObserver observer = (IObserver) simulator;
        company.addObserver(observer);

        // Start simulation with at least 5 requests
        for (int i = 0; i < 5; i++) {
            int userId = ApplicationLibrary.rand(users.size());
            users.get(userId).requestService(false, "Silent");
        }

        // Show status each iteration
        while (true) {
            boolean allVehiclesFree = true;
            for (IVehicle vehicle : vehicles) {
                if (!vehicle.isFree()) {
                    allVehiclesFree = false;
                    break;
                }
            }
            if (allVehiclesFree) {
                break; // End simulation when all vehicles are free
            }

            // Randomly request a service
            if (ApplicationLibrary.rand() % 2 == 0) {
                int userId = ApplicationLibrary.rand(users.size());
                users.get(userId).requestService(false, "Silent");
            }

            // Update and show status
            simulator.update();
        }

        // Show statistics at the end
        simulator.showStatistics();
    }
}
