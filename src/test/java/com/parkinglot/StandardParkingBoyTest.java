package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StandardParkingBoyTest {
    
    @Test
    void should_return_a_parking_ticket_when_standard_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();

        //When
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        //Then
        assertNotNull(parkingTicket);

    }
}