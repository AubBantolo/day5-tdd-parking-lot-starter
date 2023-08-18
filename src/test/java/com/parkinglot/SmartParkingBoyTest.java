package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    @Test
    void should_return_a_parking_ticket_when_smart_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        //When
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_fetched_car_when_smart_parking_boy_fetch_the_car_given_a_parking_ticket() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);

        //When
        Car fetchedCar = smartParkingBoy.fetchCar(parkingTicket);

        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_fetched_cars_when_smart_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = smartParkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.parkCar(car2);

        //When
        Car fetchedCar1 = smartParkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = smartParkingBoy.fetchCar(parkingTicket2);

        //Then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_not_return_a_car_when_smart_parking_boy_fetch_the_car_given_a_wrong_parking_ticket() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetchCar(wrongParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_smart_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        ParkingTicket usedParkingTicket = smartParkingBoy.parkCar(car);
        smartParkingBoy.fetchCar(usedParkingTicket);

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetchCar(usedParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_nothing_when_parking_given_parking_lot_with_full_capacity_10() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        IntStream.range(0, 10)
                .forEach(value -> smartParkingBoy.parkCar(new Car()));

        //When
        ParkingException parkingException = assertThrows(ParkingException.class, () -> {
            smartParkingBoy.parkCar(car);
        });

        //Then
        assertEquals("No Available Position.", parkingException.getMessage());
    }
    @Test
    void should_return_car_will_parked_to_the_second_parking_lot_when_park_given_smart_parking_boy_manage_2_parking_lots_with_available_position() {
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