package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkngBoy {

    private Car car = new Car();
    private List<ParkingLot> parkingLots;
    public SuperParkngBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }
    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket park(Car car) {
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
