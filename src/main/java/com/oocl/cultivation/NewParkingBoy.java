package com.oocl.cultivation;

import java.util.List;

public class NewParkingBoy {

    protected List<ParkingLot> parkingLots;
    protected ParkingLot chooseParkingLot;
    protected String lastErrorMessage;

    public NewParkingBoy(List<ParkingLot> parkingLots) {this.parkingLots = parkingLots; }

    public ParkingTicket park(Car car) {
        if (isExistParkingSpace()){
            lastErrorMessage = null;
            ParkingTicket ticket = chooseParkingLot.park(car);
            ticket.setParkingLot(chooseParkingLot);
            return ticket;
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
                chooseParkingLot = ticket.getParkingLot();
                return chooseParkingLot.fetch(ticket);
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
        chooseParkingLot = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots){
            if (parkingLot.isExistCapacity()){
                chooseParkingLot = parkingLot;
            }
        }
        return chooseParkingLot;
    }

    public boolean isExistParkingSpace(){
        return chooseParkingLot().getAvailableParkingPosition() > 0;
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
