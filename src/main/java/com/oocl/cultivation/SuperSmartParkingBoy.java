package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends SmartParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {super(parkingLots); }

    @Override
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

}
