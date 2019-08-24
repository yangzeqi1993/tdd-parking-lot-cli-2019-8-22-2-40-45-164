package com.oocl.cultivation;

import java.util.List;

public class ServiceManager extends NewParkingBoy {

    private NewParkingBoy newParkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;
    private String chooseParkingBoy;

    public ServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.chooseParkingBoy = "oneself";
    }
    public ServiceManager(NewParkingBoy newParkingBoy, List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.newParkingBoy = newParkingBoy;
        this.chooseParkingBoy = "newParkingBoy";
    }
    public ServiceManager(SmartParkingBoy smartParkingBoy,List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.smartParkingBoy = smartParkingBoy;
        this.chooseParkingBoy = "smartParkingBoy";
    }
    public ServiceManager(SuperSmartParkingBoy superSmartParkingBoy,List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.superSmartParkingBoy = superSmartParkingBoy;
        this.chooseParkingBoy = "superSmartParkingBoy";
    }

    public void setNewParkingBoy(NewParkingBoy newParkingBoy) {
        this.newParkingBoy = newParkingBoy;
        chooseParkingBoy = "newParkingBoy";
    }

    public void setSmartParkingBoy(SmartParkingBoy smartParkingBoy) {
        this.smartParkingBoy = smartParkingBoy;
        chooseParkingBoy = "smartParkingBoy";
    }

    public void setSuperSmartParkingBoy(SuperSmartParkingBoy superSmartParkingBoy) {
        this.superSmartParkingBoy = superSmartParkingBoy;
        chooseParkingBoy = "superSmartParkingBoy";
    }

    public void setOneself() {
        chooseParkingBoy = "oneself";
    }

    private ParkingTicket parkByNewParkingBoy(Car car) {
        ParkingTicket ticket = newParkingBoy.park(car);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return ticket;
    }

    private ParkingTicket parkBySmartParkingBoy(Car car) {
        ParkingTicket ticket = smartParkingBoy.park(car);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return ticket;
    }

    private ParkingTicket parkBySuperSmartParkingBoy(Car car) {
        ParkingTicket ticket = superSmartParkingBoy.park(car);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return ticket;
    }

    private Car fetchByNewParkingBoy(ParkingTicket ticket){
        Car car = newParkingBoy.fetch(ticket);
        lastErrorMessage = newParkingBoy.getLastErrorMessage();
        return car;
    }

    private Car fetchBySmartParkingBoy(ParkingTicket ticket){
        Car car = smartParkingBoy.fetch(ticket);
        lastErrorMessage = smartParkingBoy.getLastErrorMessage();
        return car;
    }

    private Car fetchBySuperSmartParkingBoy(ParkingTicket ticket){
        Car car = superSmartParkingBoy.fetch(ticket);
        lastErrorMessage = superSmartParkingBoy.getLastErrorMessage();
        return car;
    }

    public ParkingTicket parkForClient(Car car) {
        switch (chooseParkingBoy){
            case "newParkingBoy" :
                return parkByNewParkingBoy(car);
            case "smartParkingBoy" :
                return parkBySmartParkingBoy(car);
            case "superSmartParkingBoy" :
                return parkBySuperSmartParkingBoy(car);
            case "oneself":
                return super.park(car);
            default:
                return null;
        }
    }

    public Car fetchForClient(ParkingTicket ticket) {
        switch (chooseParkingBoy){
            case "newParkingBoy" :
                return fetchByNewParkingBoy(ticket);
            case "smartParkingBoy" :
                return fetchBySmartParkingBoy(ticket);
            case "superSmartParkingBoy" :
                return fetchBySuperSmartParkingBoy(ticket);
            case "oneself":
                return super.fetch(ticket);
            default:
                return null;
        }
    }

}
