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


    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkingTicketCarMap.get(parkingTicket);

        if(parkingTicketCarMap.get(parkingTicket) == null){
            throw new UnrecognizedTicketException();
        }
        return parkingTicketCarMap.remove(parkingTicket);
    }


}
