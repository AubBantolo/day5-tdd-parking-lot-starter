package com.parkinglot;

import java.util.List;

public class ParkingBoy {

    private final ParkingStrategy parkingStrategy;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingStrategy parkingStrategy, ParkingLot... parkingLots) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLots = List.of(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return parkingStrategy.park(car, parkingLots);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .findAny()
                .orElseThrow(() -> new UnrecognizedTicketException());
    }
}
