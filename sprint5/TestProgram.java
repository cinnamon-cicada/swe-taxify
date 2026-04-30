package sprint5;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class TestProgram {
    public static void main(String[] args) {

        // Declare drivers
        IDriver driverA = new Driver("Driver A", 'F', LocalDate.of(1990, 7, 19), 5, 4.8);
        IDriver driverB = new Driver("Driver B", 'M', LocalDate.of(1990, 7, 19), 5, 4.8);

        // Create users
        List<IUser> users = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            char gender = (i % 2 == 0) ? 'M' : 'F';
            LocalDate birthDate = LocalDate.of(1990 + (i % 10), 1 + (i % 12), 1 + (i % 28));
            users.add(new User(i, "First" + i, "Last" + i, gender, birthDate));
        }

        // Vehicle list
        List<IVehicle> vehicles = new ArrayList<>();

        // Taxi company
        TaxiCompany company = new TaxiCompany("TaxiCo", users, vehicles);

        // Assign company to users
        for (IUser user : users) {
            user.setCompany(company);
        }

        // Create taxis and shuttles
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

        // --- ADD MICRO-VEHICLES ---
        IVehicle scooter = new Scooter(100, ApplicationLibrary.randomLocation(), (Person) driverA, company);
        vehicles.add(scooter);

        IVehicle bike1 = new Bike(101, ApplicationLibrary.randomLocation(), (Person) driverB, company);
        IVehicle bike2 = new Bike(102, ApplicationLibrary.randomLocation(), (Person) driverA, company);
        IVehicle bike3 = new Bike(103, ApplicationLibrary.randomLocation(), (Person) driverB, company);

        vehicles.add(bike1);
        vehicles.add(bike2);
        vehicles.add(bike3);

        // Simulator
        IApplicationSimulator simulator = new ApplicationSimulator(company, users, vehicles);
        IObserver observer = (IObserver) simulator;
        company.addObserver(observer);

        // --- FORCED RENTALS ---
        users.get(0).requestRentalService(RentalVehicleType.SCOOTER);
        users.get(1).requestRentalService(RentalVehicleType.SCOOTER);
        users.get(2).requestRentalService(RentalVehicleType.SCOOTER);
        users.get(3).requestRentalService(RentalVehicleType.SCOOTER);

        users.get(4).requestRentalService(RentalVehicleType.BIKE);
        users.get(5).requestRentalService(RentalVehicleType.BIKE);
        users.get(6).requestRentalService(RentalVehicleType.BIKE);

        // // Initial taxi requests
        // for (int i = 0; i < 5; i++) {
        //     int userId = ApplicationLibrary.rand(users.size());
        //     users.get(userId).requestService(false, "Silent");
        // }

        // Simulation loop
        while (true) {
            boolean allVehiclesFree = true;

            for (IVehicle vehicle : vehicles) {
                if (!vehicle.isFree()) {
                    allVehiclesFree = false;
                    break;
                }
            }

            if (allVehiclesFree) break;

            // Randomly request services
            // if (ApplicationLibrary.rand() % 2 == 0) {
            //     int userId = ApplicationLibrary.rand(users.size());
            //     users.get(userId).requestService(false, "Silent");
            // }

            // Rent a scooter repeatedly to test battery depletion and charging
            users.get(0).requestRentalService(RentalVehicleType.SCOOTER);

            simulator.update();
        }
        users.get(0).requestRentalService(RentalVehicleType.SCOOTER);
        simulator.update();
        simulator.showStatistics();
    }
}