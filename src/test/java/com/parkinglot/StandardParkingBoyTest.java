package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {
    
    @Test
    void should_return_a_parking_ticket_when_standard_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        //Given
        Car car = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);

    }
    
    @Test
    void should_return_fetched_car_when_standard_parking_boy_fetch_the_car_given_a_parking_ticket() {
        //Given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        
        //When
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_fetched_cars_when_standard_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //When
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        //Then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_not_return_a_car_when_standard_parking_boy_fetch_the_car_given_a_wrong_parking_ticket() {
        //Given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());

        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(wrongParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_standard_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        //Given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());

        ParkingTicket usedParkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedParkingTicket);

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(usedParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_nothing_when_parking_given_parking_lot_with_full_capacity_10() {
        //Given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), new ParkingLot());

        IntStream.range(0, 10)
                .forEach(value -> parkingBoy.park(new Car()));

        //When
        ParkingException parkingException = assertThrows(ParkingException.class, () -> {
            parkingBoy.park(car);
        });

        //Then
        assertEquals("No Available Position.", parkingException.getMessage());
    }

    @Test
    void should_car_will_parked_to_the_first_parking_lot_when_park_given_standard_parking_boy_manage_2_parking_lots_with_available_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car));
        assertFalse(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_car_will_parked_to_the_second_parking_lot_when_park_given_standard_parking_boy_manage_2_parking_lots_1_is_full_2_available_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        IntStream.range(0, 10).forEach(value -> {
            parkingBoy.park(new Car());
        });

        parkingBoy.park(car);

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car));
        assertTrue(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_return_right_car_when_fetch_car_twice_given_standard_parking_boy_manage_2_parking_lots_both_with_a_parked_car_2_parking_ticket() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //When
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        //Then
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_when_fetch_car_given_standard_parking_boy_manage_2_parking_lots_both_with_an_unrecognized_parking_ticket() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(unrecognizedParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_car_given_standard_parking_boy_manage_2_parking_lots_both_with_a_used_parking_ticket() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        ParkingTicket usedParkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(usedParkingTicket);

        //When
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(usedParkingTicket);
        });

        //Then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_car_given_standard_parking_boy_manage_2_parking_lots_both_with_any_position() {
        //Given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoy(), parkingLot1, parkingLot2);

        //When
        IntStream.range(0, 20)
                .forEach(value -> parkingBoy.park(new Car()));

        //When
        ParkingException parkingException = assertThrows(ParkingException.class, () -> {
            parkingBoy.park(car);
        });

        //Then
        assertEquals("No Available Position.", parkingException.getMessage());
    }
}