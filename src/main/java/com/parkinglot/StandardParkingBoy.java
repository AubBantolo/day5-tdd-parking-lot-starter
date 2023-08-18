package com.parkinglot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardParkingBoy {

    private Car car = new Car();
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasAvailableSlots())
                .findFirst()
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
