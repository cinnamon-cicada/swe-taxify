# SWE-Taxify (Sprint 4)

This folder contains the Sprint4 implementation of a simple taxi service simulation in Java.

## Overview

This sprint focuses on the implementation of the IDriver and Driver classes, aswell as the introduction of Pink Rides and RideTypes (Silent/Standered). Other classes are updated to accomadate these changes.

## Classes

- `ApplicationLibrary`: Provides utility methods for generating random data and calculating distances.
- `ApplicationSimulator`: Acts as the main orchestrator of the taxi company simulation.
- `Driver`: Represents a driver with profile details such as name, gender, birth date, experience, and rating.
- `Location`: Represents a location on a 10x10 grid with x and y coordinates.
- `Route`: Represents a route between two locations, including distance calculation.
- `Service`: Represents a service request with pickup and dropoff locations, user, and rating.
- `Statistics`: Tracks various statistics such as number of services, total distance, total billing, and average rating.
- `User`: Represents a user with an ID and name.
- `Vehicle`: Base class for all vehicles, implementing the IVehicle interface.
- `Taxi`: A standard taxi vehicle that extends Vehicle.
- `TaxiCompany`: Represents the core service provider in the simulation.
- `Shuttle`: A shuttle vehicle that extends Vehicle with a cost multiplier of 1.5.
- `VehicleStatus`: Enum representing the status of a vehicle (e.g., free, busy).

## Interfaces
- `IApplicationSimulator`: Defines methods for ApplicationSimulator
- `IDriver`: Defines methods for Driver
- `IObserver`: Defines methods for Observer
- `ILocation`: Defines methods for location objects.
- `IMovable`: Defines methods for objects that can move.
- `IRoute`: Defines methods for route objects.
- `IService`: Defines methods for service requests.
- `IStatistics`: Defines methods for statistics tracking.
- `ITaxiCompany`: Defines methods for taxi company operations.
- `IUser`: Defines methods for user objects.
- `IVehicle`: Defines methods for vehicle objects, including movement, service management, and cost calculation.


Suppose the CEO of the company decides to enter the market of micro-mobility services with electric scooters and bikes. Redesign the system to provide these services. Users can find the nearest vehicle to their location and make a reservation to ensure the vehicle will be available. Unlocking microvehicles costs 1.0 euro. The standard rate for scooters is 0.15 euros, and the rate for bikes is 0.25 euros. Scooters and bikes have three different statuses: free, booked, or in a ride. It is also important to note that in order to book a scooter, the battery must be fully charged. The battery consumption of one ride is 25%. Scooters must be recharged after 3 rides, when the battery is below or equal 25%.

