package com.oocl.cultivation;

import java.util.List;

public class NewParkingBoy {

    private List<ParkingLot> parkingLots;
    private ParkingLot chooseParkingLot;
    private String lastErrorMessage;

    public NewParkingBoy(List<ParkingLot> parkingLots) {this.parkingLots = parkingLots; }

    public ParkingTicket park(Car car) {
        if (isExistParkingSpace()){
            lastErrorMessage = null;
            return chooseParkingLot().park(car);
        }else {
            lastErrorMessage = "The parking lot is full.";
            return null;
        }

    }

    public Car fetch(ParkingTicket ticket) {
        if(ticket != null) {
            if (!isExistCar(ticket)) {
                lastErrorMessage = "Unrecognized parking ticket.";
                return null;
            }else {
                lastErrorMessage = "";
                return this.chooseParkingLot.fetch(ticket);
            }
        }else {
            lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }

    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public ParkingLot chooseParkingLot(){
        for (ParkingLot parkingLot : parkingLots){
            if (parkingLot.isExistCapacity()){
                chooseParkingLot = parkingLot;
                return parkingLot;
            }
        }
        return null;
    }

    public boolean isExistParkingSpace(){
        return chooseParkingLot() != null;
    }

    public boolean isExistCar(ParkingTicket ticket){
        for (ParkingLot parkingLot : parkingLots){
            if(parkingLot.isExistCar(ticket)){
                return true;
            }
        }
        return false;
    }

}
