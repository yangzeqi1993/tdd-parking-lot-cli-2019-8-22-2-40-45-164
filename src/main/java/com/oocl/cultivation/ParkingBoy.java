package com.oocl.cultivation;

public class ParkingBoy {

    private ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {this.parkingLot = parkingLot; }

    public ParkingTicket park(Car car) {
    	if (parkingLot.isExistCapacity()){
			lastErrorMessage = null;
			return parkingLot.park(car);
		}else {
			lastErrorMessage = "The parking lot is full.";
    		return null;
		}

    }

    public Car fetch(ParkingTicket ticket) {
    	if(ticket != null) {
        	if (!parkingLot.isExistCar(ticket)) {
        		lastErrorMessage = "Unrecognized parking ticket.";
        		return null;
        	}else {
        		lastErrorMessage = "";
        		return parkingLot.fetch(ticket);
        	}
    	}else {
    		lastErrorMessage = "Please provide your parking ticket.";
			return null;
		}

    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

}
