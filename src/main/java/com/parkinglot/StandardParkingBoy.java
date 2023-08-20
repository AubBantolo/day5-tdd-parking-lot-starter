package com.parkinglot;

import java.util.List;

public class StandardParkingBoy implements ParkingStrategy{

    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasAvailableSlots())
                .findFirst()
                .orElseThrow(() -> new ParkingException())
                .park(car);
    }
}
