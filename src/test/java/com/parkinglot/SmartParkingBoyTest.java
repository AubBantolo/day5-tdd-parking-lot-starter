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
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_fetched_car_when_smart_parking_boy_fetch_the_car_given_a_parking_ticket() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //When
        Car fetchedCar = smartParkingBoy.fetch(parkingTicket);

        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_fetched_cars_when_smart_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car1);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);

        //When
        Car fetchedCar1 = smartParkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = smartParkingBoy.fetch(parkingTicket2);

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
            smartParkingBoy.fetch(wrongParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_smart_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        //Given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        ParkingTicket usedParkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(usedParkingTicket);

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetch(usedParkingTicket);
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
                .forEach(value -> smartParkingBoy.park(new Car()));

        //When
        ParkingException parkingException = assertThrows(ParkingException.class, () -> {
            smartParkingBoy.park(car);
        });

        //Then
        assertEquals("No Available Position.", parkingException.getMessage());
    }
    @Test
    void should_return_car_will_parked_to_the_second_parking_lot_when_park_given_smart_parking_boy_manage_2_parking_lots_with_more_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(20);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car));
        assertTrue(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_return_car_will_parked_to_the_first_parking_lot_when_park_given_smart_parking_boy_manage_2_parking_lots_with_more_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(20);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car));
        assertFalse(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_return_car_will_parked_to_the_second_parking_lot_when_park_given_smart_parking_boy_manage_2_parking_lots_with_more_position_with_parked_cars() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(20);
        setInitiallyParkedCars(parkingLot1, 15);

        ParkingLot parkingLot2 = new ParkingLot(10);
        setInitiallyParkedCars(parkingLot2, 4);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

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