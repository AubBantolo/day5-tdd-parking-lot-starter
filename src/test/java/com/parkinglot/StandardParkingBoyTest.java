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

    @Test
    void should_return_fetched_cars_when_standard_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        ParkingTicket parkingTicket1 = standardParkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);

        //When
        Car fetchedCar1 = standardParkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = standardParkingBoy.fetchCar(parkingTicket2);

        //Then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_not_return_a_car_when_standard_parking_boy_fetch_the_car_given_a_wrong_parking_ticket() {
        //Given
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();

        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //When

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(wrongParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_standard_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        //Given
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();

        ParkingTicket usedParkingTicket = standardParkingBoy.parkCar(car);
        standardParkingBoy.fetchCar(usedParkingTicket);

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(usedParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());

    }
}