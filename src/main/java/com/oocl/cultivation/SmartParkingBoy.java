package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends NewParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {super(parkingLots); }

    @Override
    public ParkingLot chooseParkingLot(){
        ParkingLot maxParkingLot = parkingLots.get(0);
        for (ParkingLot parkingLot : parkingLots){
            if (parkingLot.isExistCapacity()){
                if(maxParkingLot.getAvailableParkingPosition() < parkingLot.getAvailableParkingPosition()){
                    maxParkingLot = parkingLot;
                }
            }
        }
        chooseParkingLot = maxParkingLot;
        return maxParkingLot;
    }

}
