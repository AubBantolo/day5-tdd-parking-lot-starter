package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_parking_lot_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = parkingLot.park(car);

        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_the_parked_car_when_fetch_given_parking_lot_and_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //When
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        //Then

        assertEquals(car, fetchedCar);
    }
}