package com.parkinglot;

public interface ParkingStrategy {

    ParkingTicket park (Car Car);

    Car fetch(ParkingTicket parkingTicket);
}
