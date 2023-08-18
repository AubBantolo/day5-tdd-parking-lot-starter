package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class StandardParkingBoy {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingTicket parkCar(Car car) {

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }


    public Car fetchCar(ParkingTicket parkingTicket) {
        if (parkingTicketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return parkingTicketCarMap.remove(parkingTicket);
    }
}
