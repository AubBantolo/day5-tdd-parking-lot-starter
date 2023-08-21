package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy implements ParkingStrategy {
    //TODO: Check the code smells for refactoring.
    @Override
    public ParkingTicket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getLargestAvailablePositionRate))
                .orElseThrow(() -> new ParkingException())
                .park(car);
    }
}
