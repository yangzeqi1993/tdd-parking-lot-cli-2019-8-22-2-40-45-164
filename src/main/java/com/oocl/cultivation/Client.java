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

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    // by parkingBoy
    public ParkingTicket park(ParkingBoy parkingBoy) {
        ParkingTicket ticket = parkingBoy.park(car);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return ticket;
    }
    public Car fetch(ParkingBoy parkingBoy, ParkingTicket ticket){
        Car car = parkingBoy.fetch(ticket);
        lastErrorMessage = parkingBoy.getLastErrorMessage();
        return car;
    }

    // by newParkingBoy
    public ParkingTicket park(NewParkingBoy newParkingBoy) {
        ParkingTicket ticket = newParkingBoy.park(car);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return ticket;
    }
    public Car fetch(NewParkingBoy newParkingBoy, ParkingTicket ticket){
        Car car = newParkingBoy.fetch(ticket);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return car;
    }

    // by smartParkingBoy
    public ParkingTicket park(SmartParkingBoy smartParkingBoy) {
        ParkingTicket ticket = smartParkingBoy.park(car);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return ticket;
    }
    public Car fetch(SmartParkingBoy smartParkingBoy, ParkingTicket ticket){
        Car car = smartParkingBoy.fetch(ticket);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return car;
    }

    // by superSmartParkingBoy
    public ParkingTicket park(SuperSmartParkingBoy superSmartParkingBoy) {
        ParkingTicket ticket = superSmartParkingBoy.park(car);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return ticket;
    }
    public Car fetch(SuperSmartParkingBoy superSmartParkingBoy, ParkingTicket ticket){
        Car car = superSmartParkingBoy.fetch(ticket);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return car;
    }

    // by serviceManager
    public ParkingTicket park(ServiceManager serviceManager) {
        ParkingTicket ticket = serviceManager.parkForClient(car);
        lastErrorMessage = serviceManager.getLastErrorMessage();
        return ticket;
    }
    public Car fetch(ServiceManager serviceManager, ParkingTicket ticket){
        Car car = serviceManager.fetchForClient(ticket);
        lastErrorMessage = serviceManager.getLastErrorMessage();
        return car;
    }

}
