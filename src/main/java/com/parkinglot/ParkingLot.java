package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Car car = new Car();
    final int DEFAULT_CAPACITY;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingLot() {
        DEFAULT_CAPACITY = 10;
    }

    public List<Car> getParkedCars() {
        return new ArrayList<>(parkingTicketCarMap.values());
    }

    public ParkingTicket park(Car car) {

        if (parkingTicketCarMap.size() == DEFAULT_CAPACITY) {
            throw new ParkingException();
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }


    public Car fetch(ParkingTicket parkingTicket) {

        if (parkingTicketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return parkingTicketCarMap.remove(parkingTicket);
    }

    public boolean hasAvailableSlots() {
        return parkingTicketCarMap.size() < DEFAULT_CAPACITY;
    }
}
