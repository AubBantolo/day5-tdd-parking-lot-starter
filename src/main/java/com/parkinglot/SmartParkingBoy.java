package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy implements ParkingStrategy {

    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparing(parkingLot -> parkingLot.getAvailableSlots()))
                .orElseThrow(() -> new ParkingException())
                .park(car);
    }
}
