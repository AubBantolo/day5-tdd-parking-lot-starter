package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {

    ParkingTicket park (Car car, List<ParkingLot> parkingLots);
}
