package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = smartParkingBoy.park(car);
        Car fetched = smartParkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = smartParkingBoy.park(firstCar);
        ParkingTicket secondTicket = smartParkingBoy.park(secondCar);

        Car fetchedByFirstTicket = smartParkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = smartParkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = smartParkingBoy.park(car);

        assertNull(smartParkingBoy.fetch(wrongTicket));
        assertSame(car, smartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        smartParkingBoy.fetch(wrongTicket);
        String message = smartParkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        smartParkingBoy.fetch(wrongTicket);
        assertNotNull(smartParkingBoy.getLastErrorMessage());

        ParkingTicket ticket = smartParkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(smartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = smartParkingBoy.park(car);

        assertNull(smartParkingBoy.fetch(null));
        assertSame(car, smartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        smartParkingBoy.fetch(null);

        assertEquals(
                "Please provide your parking ticket.",
                smartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);

        assertNull(smartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);
        smartParkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                smartParkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_get_message_if_parkingLots_are_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());


        assertEquals(null, smartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        assertNull(smartParkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_parkingLots_are_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());


        assertEquals("The parking lot is full.", smartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_park_a_car_by_a_parking_boy_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket ticket = client.park(smartParkingBoy);
        Car fetched = client.fetch(smartParkingBoy,ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_by_a_parking_boy_and_get_it_back() {
        Car car_1 = new Car();
        Client client_1 = new Client(car_1);

        Car car_2 = new Car();
        Client client_2 = new Client(car_2);

        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket ticket_1 = client_1.park(smartParkingBoy);
        Car fetched_1 = client_1.fetch(smartParkingBoy,ticket_1);

        ParkingTicket ticket_2 = client_2.park(smartParkingBoy);
        Car fetched_2 = client_2.fetch(smartParkingBoy,ticket_2);

        assertSame(fetched_1, car_1);
        assertSame(fetched_2, car_2);
    }

    @Test
    void should_client_not_fetch_any_car_once_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = client.park(smartParkingBoy);

        assertNull(client.fetch(smartParkingBoy,wrongTicket));
        assertSame(car, client.fetch(smartParkingBoy,ticket));
    }

    @Test
    void should_client_query_message_once_the_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        client.fetch(smartParkingBoy,wrongTicket);
        String message = client.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_client_query_message_once_ticket_is_not_provided() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        client.fetch(smartParkingBoy,null);
        String message = client.getLastErrorMessage();

        assertEquals("Please provide your parking ticket.", message);
    }

    @Test
    void should_client_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.park(smartParkingBoy);
        client.setCar(new Car());
        client.park(smartParkingBoy);
        client.setCar(new Car());

        assertNull(client.park(smartParkingBoy));
    }

    @Test
    void should_client_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.park(smartParkingBoy);
        client.setCar(new Car());
        client.park(smartParkingBoy);
        client.setCar(new Car());
        client.park(smartParkingBoy);

        assertEquals("The parking lot is full.", client.getLastErrorMessage());
    }

    @Test
    void should_client_park_cars_to_most_park_space_parking_lot() {
        final int capacity_1 = 1;
        final int capacity_2 = 2;
        final int capacity_3 = 3;
        ParkingLot parkingLot_1 = new ParkingLot(capacity_1);
        ParkingLot parkingLot_2 = new ParkingLot(capacity_2);
        ParkingLot parkingLot_3 = new ParkingLot(capacity_3);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2, parkingLot_3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        assertEquals(parkingLot_3,smartParkingBoy.chooseParkingLot());
        client.park(smartParkingBoy);
        assertEquals(2, parkingLot_3.getAvailableParkingPosition());
    }

    @Test
    void should_client_park_multiply_cars_to_most_park_space_parking_lot() {
        final int capacity_1 = 1;
        final int capacity_2 = 2;
        final int capacity_3 = 3;
        ParkingLot parkingLot_1 = new ParkingLot(capacity_1);
        ParkingLot parkingLot_2 = new ParkingLot(capacity_2);
        ParkingLot parkingLot_3 = new ParkingLot(capacity_3);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2, parkingLot_3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.park(smartParkingBoy);

        client.setCar(new Car());
        assertEquals(parkingLot_2,smartParkingBoy.chooseParkingLot());
        client.park(smartParkingBoy);
        assertEquals(1, parkingLot_2.getAvailableParkingPosition());


    }
}
