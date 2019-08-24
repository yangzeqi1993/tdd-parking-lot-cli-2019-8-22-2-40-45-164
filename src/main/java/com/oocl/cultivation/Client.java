package com.oocl.cultivation;

public class Client {
    private Car car;
    private String lastErrorMessage;

    public Client(Car car){
        this.car = car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingTicket park(ParkingBoy parkingBoy) {
        ParkingTicket ticket = parkingBoy.park(car);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return ticket;
    }

    public ParkingTicket parkByNewParkingBoy(NewParkingBoy newParkingBoy) {
        ParkingTicket ticket = newParkingBoy.park(car);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return ticket;
    }

    public ParkingTicket parkBySmartParkingBoy(SmartParkingBoy smartParkingBoy) {
        ParkingTicket ticket = smartParkingBoy.park(car);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return ticket;
    }

    public ParkingTicket parkBySuperSmartParkingBoy(SuperSmartParkingBoy superSmartParkingBoy) {
        ParkingTicket ticket = superSmartParkingBoy.park(car);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return ticket;
    }

    public Car fetch(ParkingBoy parkingBoy, ParkingTicket ticket){
        Car car = parkingBoy.fetch(ticket);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return car;
    }

    public Car fetchByNewParkingBoy(NewParkingBoy newParkingBoy, ParkingTicket ticket){
        Car car = newParkingBoy.fetch(ticket);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return car;
    }

    public Car fetchBySmartParkingBoy(SmartParkingBoy smartParkingBoy, ParkingTicket ticket){
        Car car = smartParkingBoy.fetch(ticket);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return car;
    }

    public Car fetchBySuperSmartParkingBoy(SuperSmartParkingBoy superSmartParkingBoy, ParkingTicket ticket){
        Car car = superSmartParkingBoy.fetch(ticket);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
