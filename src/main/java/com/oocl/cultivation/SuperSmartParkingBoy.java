package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy {
    private List<ParkingLot> parkingLots;
    private String lastErrorMessage;
    private ParkingLot chooseParkingLot;

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {this.parkingLots = parkingLots; }

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
                lastErrorMessage = "";
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
        ParkingLot maxParkingLot = parkingLots.get(0);
        float maxSpaceRate = maxParkingLot.getAvailableParkingPosition()/maxParkingLot.getCapacity();
        for (ParkingLot parkingLot : parkingLots){
            float spaceRate = parkingLot.getAvailableParkingPosition()/parkingLot.getCapacity();
            if (parkingLot.isExistCapacity()){
                if(maxSpaceRate < spaceRate){
                    maxParkingLot = parkingLot;
                }
                if(maxSpaceRate == spaceRate){
                    if(maxParkingLot.getAvailableParkingPosition() < parkingLot.getAvailableParkingPosition()){
                        maxParkingLot = parkingLot;
                    }
                }
            }
        }
        chooseParkingLot = maxParkingLot;
        return maxParkingLot;
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
