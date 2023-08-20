package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy implements ParkingStrategy {

    private List<ParkingLot> parkingLots;
    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }
    @Override
    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(parkingLot -> parkingLot.getAvailableSlots()))
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
