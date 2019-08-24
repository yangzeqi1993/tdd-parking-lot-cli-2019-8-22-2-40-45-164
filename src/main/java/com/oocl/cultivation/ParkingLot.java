package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

	public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return ticket;
	}

    public Car fetch(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
	}

	public boolean isExistCar(ParkingTicket ticket){
        return cars.get(ticket) != null;
    }

    public boolean isExistCapacity(){
        return getAvailableParkingPosition() > 0;
    }

}
