package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy {
    private Car car = new Car();
    private List<ParkingLot> parkingLots;
    public SuperParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getLargestAvailablePositionRate))
                .orElseThrow(() -> new ParkingException())
                .park(car);
    }


    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .findAny()
                .orElseThrow(() -> new UnrecognizedTicketException());
    }

}
