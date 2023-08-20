package com.parkinglot;

import java.util.List;

public class StandardParkingBoy implements ParkingStrategy{

    private Car car = new Car();
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = List.of(parkingLots);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    @Override
    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasAvailableSlots())
                .findFirst()
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
