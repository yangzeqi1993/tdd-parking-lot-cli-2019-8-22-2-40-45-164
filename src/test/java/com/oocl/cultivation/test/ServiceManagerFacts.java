package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceManagerFacts {
    @Test
    void should_a_service_manager_park_a_car_by_a_new_parking_boy_and_get_it_back() {
        Car car = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(newParkingBoy,parkingLots);

        ParkingTicket ticket = serviceManager.parkForClient(car);
        Car fetched = serviceManager.fetchForClient(ticket);
        assertSame(fetched, car);
    }

    @Test
    void should_a_service_manager_park_a_car_by_a_super_smart_parking_boy_and_get_it_back() {
        Car car = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(superSmartParkingBoy,parkingLots);

        ParkingTicket ticket = serviceManager.parkForClient(car);
        Car fetched = serviceManager.fetchForClient(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_a_service_manager_park_a_car_by_himself_y_and_get_it_back() {
        Car car = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        ParkingTicket ticket = serviceManager.park(car);
        Car fetched = serviceManager.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_a_service_manager_park_multiple_cars_by_different_parking_boys_and_get_them_back() {
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(newParkingBoy,parkingLots);


        ParkingTicket firstTicket = serviceManager.parkForClient(firstCar);
        serviceManager.setSmartParkingBoy(smartParkingBoy);
        ParkingTicket secondTicket = serviceManager.parkForClient(secondCar);

        serviceManager.setOneself();
        Car fetchedByFirstTicket = serviceManager.fetchForClient(firstTicket);
        serviceManager.setNewParkingBoy(newParkingBoy);
        Car fetchedBySecondTicket = serviceManager.fetchForClient(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_a_service_manager_fetch_cars_use_smart_parking_boy_from_other_parking_lot_and_get_them_back() {
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots_1 =  new ArrayList<>();
        parkingLots_1.add(parkingLot_1);
        List<ParkingLot> parkingLots_2 =  new ArrayList<>();
        parkingLots_2.add(parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots_1);
        ServiceManager serviceManager = new ServiceManager(smartParkingBoy,parkingLots_2);


        ParkingTicket firstTicket = serviceManager.parkForClient(firstCar);
        ParkingTicket secondTicket = serviceManager.parkForClient(secondCar);

        Car fetchedByFirstTicket = serviceManager.fetchForClient(firstTicket);
        serviceManager.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);

        String message = serviceManager.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_a_service_manager_fetch_cars_by_new_parking_boy_from_other_parking_lot_and_get_them_back() {
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots_1 =  new ArrayList<>();
        parkingLots_1.add(parkingLot_1);
        List<ParkingLot> parkingLots_2 =  new ArrayList<>();
        parkingLots_2.add(parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots_1);
        ServiceManager serviceManager = new ServiceManager(parkingLots_2);


        ParkingTicket firstTicket = serviceManager.park(firstCar);
        ParkingTicket secondTicket = serviceManager.parkForClient(secondCar);

        Car fetchedByFirstTicket = serviceManager.fetchForClient(firstTicket);
        newParkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);

        String message = newParkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_a_client_park_a_car_by_a_service_manager_use_himself_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        ParkingTicket ticket = client.park(serviceManager);
        Car fetched = client.fetch(serviceManager,ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_a_client_park_a_car_by_a_service_manager_use_super_smart_parking_boy_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(superSmartParkingBoy,parkingLots);

        ParkingTicket ticket = client.park(serviceManager);
        Car fetched = client.fetch(serviceManager,ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_different_clients_park_multiple_cars_by_a_service_manager_user_different_parking_boy_and_get_it_back() {
        Car car_1 = new Car();
        Client client_1 = new Client(car_1);

        Car car_2 = new Car();
        Client client_2 = new Client(car_2);

        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots_1 =  new ArrayList<>();
        parkingLots_1.add(parkingLot_1);
        List<ParkingLot> parkingLots_2 =  new ArrayList<>();
        parkingLots_2.add(parkingLot_2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots_1);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots_2);
        ServiceManager serviceManager = new ServiceManager(superSmartParkingBoy,parkingLots_1);

        ParkingTicket ticket_1 = client_1.park(serviceManager);
        Car fetched_1 = client_1.fetch(serviceManager,ticket_1);

        serviceManager.setNewParkingBoy(newParkingBoy);
        ParkingTicket ticket_2 = client_2.park(serviceManager);
        Car fetched_2 = client_2.fetch(serviceManager,ticket_2);

        assertSame(fetched_1, car_1);
        assertSame(fetched_2, car_2);

    }

    @Test
    void should_a_client_not_fetch_any_car_by_a_service_manager_use_smart_parking_boy_once_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(smartParkingBoy,parkingLots);

        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = client.park(serviceManager);

        assertNull(client.fetch(serviceManager,wrongTicket));
        assertSame(car, client.fetch(serviceManager,ticket));
    }

    @Test
    void should_a_client_query_message_by_a_service_manager_use_smart_parking_boy_once_the_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(smartParkingBoy,parkingLots);

        ParkingTicket wrongTicket = new ParkingTicket();

        assertNull(client.fetch(serviceManager,wrongTicket));
        String message = client.getLastErrorMessage();
        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_a_client_query_message_by_a_service_manager_use_himself_once_ticket_is_not_provided() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        client.fetch(serviceManager,null);
        String message = client.getLastErrorMessage();

        assertEquals("Please provide your parking ticket.", message);
    }

    @Test
    void should_a_client_not_park_cars_to_parking_lot_by_a_service_manager_use_smart_parking_boy_himself_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ServiceManager serviceManager = new ServiceManager(smartParkingBoy,parkingLots);

        Client client = new Client(new Car());
        client.park(serviceManager);

        client.setCar(new Car());
        serviceManager.setOneself();
        client.park(serviceManager);

        assertNull(client.park(smartParkingBoy));
        assertEquals("The parking lot is full.", client.getLastErrorMessage());
    }

}
