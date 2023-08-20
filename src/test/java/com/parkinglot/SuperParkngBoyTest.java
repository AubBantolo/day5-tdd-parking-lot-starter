package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SuperParkngBoyTest {
    @Test
    void should_return_car_will_parked_to_the_first_parking_lot_when_park_given_super_parking_boy_manage_2_parking_lots_with_more_position_rate() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        setInitiallyParkedCars(parkingLot1, 3);
        ParkingLot parkingLot2 = new ParkingLot(20);
        setInitiallyParkedCars(parkingLot2, 7);
        SuperParkngBoy superParkngBoy = new SuperParkngBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = superParkngBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car));
        assertFalse(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_return_car_will_parked_to_the_second_parking_lot_when_park_given_super_parking_boy_manage_2_parking_lots_with_more_position_rate() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        setInitiallyParkedCars(parkingLot1, 3);
        ParkingLot parkingLot2 = new ParkingLot(20);
        setInitiallyParkedCars(parkingLot2, 5);
        SuperParkngBoy superParkngBoy = new SuperParkngBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = superParkngBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car));
        assertTrue(parkingLot2.getParkedCars().contains(car));
    }

    private void setInitiallyParkedCars(ParkingLot parkingLot, int numberOfCarsParked) {
        IntStream.range(0, numberOfCarsParked)
                .forEach(value -> parkingLot.park(new Car()));
    }
}