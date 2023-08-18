package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car = new Car();
    final int DEFAULT_CAPACITY;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    public ParkingLot(){
        DEFAULT_CAPACITY = 10;
    }


    public ParkingTicket park(Car car) {

        if(parkingTicketCarMap.size() == DEFAULT_CAPACITY){
            throw new ParkingException();
        }
        
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
