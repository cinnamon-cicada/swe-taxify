# SWE-Taxify (Sprint 1)

This folder contains the Sprint 1 implementation of a simple taxi service simulation in Java.

## Overview

This sprint focuses on the core domain model and utility components for a taxi service, including:

- **Users** (`User`, `IUser`) and **Taxi Company** interaction (`ITaxiCompany`)
- **Service requests** (`Service`, `IService`) with pickup/dropoff and ratings
- **Location & routing** (`Location`, `Route`, `ILocation`, `IRoute`) on a 10x10 grid
- **Statistics tracking** (`Statistics`, `IStatistics`) for services, ratings, distance, and billing
- **Helper methods** (`ApplicationLibrary`) for random data and distance calculations