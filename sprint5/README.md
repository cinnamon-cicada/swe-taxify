# SWE-Taxify (Final)

This folder contains the final implementation of a simple taxi service simulation in Java.

## Overview

This final sprint focuses on the finalization of the Taxify program. It adds new MicroVehicles which include Bike and Scooter options as well as revamping Drivers and Users to be subclasses of Person. Small changes were also made to TaxiCompany and User. This is the finalized project completed with a test program implemeted!

## Classes

- `ApplicationLibrary`: Provides utility methods for generating random data and calculating distances.
- `ApplicationSimulator`: Acts as the main orchestrator of the taxi company simulation.
- `Bike`: Represents a bike which extends MicroVehicle.
- `Driver`: Represents a driver with profile details such as name, gender, birth date, experience, and rating.
- `Location`: Represents a location on a 10x10 grid with x and y coordinates.
- `MicroVehicle`: Represents an implementation of IVehichle which is avaliable for rent and needs to be additionally purchased.
- `MicroVehicleStatus`: Enum which specifies state of a MicroVehicle.
- `Person`: Represents a user with an ID and name.
- `RentalVehicleType`: Enum which specifies different MicroVehicle options.
- `Route`: Represents a route between two locations, including distance calculation.
- `Service`: Represents a service request with pickup and dropoff locations, user, and rating.
- `Scooter`: Represents a scooter which extends MicroVehicle.
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




