package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {
    
    @Test
    void should_return_a_parking_ticket_when_standard_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        //Given
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();

        //When
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);

        //Then
        assertNotNull(parkingTicket);

    }
    
    @Test
    void should_return_fetched_car_when_standard_parking_boy_fetch_the_car_given_a_parking_ticket() {
        //Given
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        
        //When
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);

        //Then
        assertEquals(car, fetchedCar);
    }
}