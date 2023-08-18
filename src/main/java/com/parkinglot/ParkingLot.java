package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car = new Car();
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();


    public ParkingTicket park(Car car) {

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);

        return parkingTicket;
    }

}
