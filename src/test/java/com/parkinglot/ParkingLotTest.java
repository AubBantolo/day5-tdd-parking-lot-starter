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

    @Test
    void should_return_right_car_with_each_ticket_when_fetch_the_car_2_given_a_parking_lot_with_2_cars() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        ParkingTicket parkingTicket2 = parkingLot.park(car2);

        //When
        Car fetchedCar1 = parkingLot.fetch(parkingTicket1);
        Car fetchedCar2 = parkingLot.fetch(parkingTicket2);

        //Then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);

        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(wrongParkingTicket);
        });

        //Car fetchedCar = parkingLot.fetch(wrongParkingTicket);

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
}