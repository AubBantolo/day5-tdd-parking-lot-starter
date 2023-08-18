package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Car car = new Car();

    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
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
        return parkingTicketCarMap.size() < capacity;
    }
}
