package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {
    @Test
    void should_return_car_will_parked_to_the_first_parking_lot_when_park_given_smart_parking_boy_manage_2_parking_lots_with_available_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(20);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //Then
        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car));
        assertTrue(parkingLot2.getParkedCars().contains(car));
    }
}