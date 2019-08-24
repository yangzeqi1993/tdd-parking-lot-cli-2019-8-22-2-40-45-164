package com.oocl.cultivation;

public class ParkingTicket {
    private ParkingLot parkingLot;

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot(){
        return this.parkingLot;
    }
}
