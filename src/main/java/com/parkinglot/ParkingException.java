package com.parkinglot;

public class ParkingException extends RuntimeException {

    public ParkingException(){
        super("No Available Position.");
    }
}
