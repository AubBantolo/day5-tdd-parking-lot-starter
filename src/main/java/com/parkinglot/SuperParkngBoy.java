package com.parkinglot;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class SuperParkngBoy implements ParkingStrategy {

    private Car car = new Car();
    private List<ParkingLot> parkingLots;
    public SuperParkngBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    @Override
    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getLargestAvailablePositionRate))
                .orElseThrow(() -> new ParkingException())
                .park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .findAny()
                .orElseThrow(() -> new UnrecognizedTicketException());
    }
}
