package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NewParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = newParkingBoy.park(car);
        Car fetched = newParkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = newParkingBoy.park(firstCar);
        ParkingTicket secondTicket = newParkingBoy.park(secondCar);

        Car fetchedByFirstTicket = newParkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = newParkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = newParkingBoy.park(car);

        assertNull(newParkingBoy.fetch(wrongTicket));
        assertSame(car, newParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        newParkingBoy.fetch(wrongTicket);
        String message = newParkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        newParkingBoy.fetch(wrongTicket);
        assertNotNull(newParkingBoy.getLastErrorMessage());

        ParkingTicket ticket = newParkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(newParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = newParkingBoy.park(car);

        assertNull(newParkingBoy.fetch(null));
        assertSame(car, newParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        newParkingBoy.fetch(null);

        assertEquals(
                "Please provide your parking ticket.",
                newParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = newParkingBoy.park(car);
        newParkingBoy.fetch(ticket);

        assertNull(newParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = newParkingBoy.park(car);
        newParkingBoy.fetch(ticket);
        newParkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                newParkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_get_message_if_parkingLots_are_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        newParkingBoy.park(new Car());
        newParkingBoy.park(new Car());


        assertEquals(null, newParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        newParkingBoy.park(new Car());
        newParkingBoy.park(new Car());

        assertNull(newParkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_parkingLots_are_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        newParkingBoy.park(new Car());
        newParkingBoy.park(new Car());
        newParkingBoy.park(new Car());


        assertEquals("The parking lot is full.", newParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_park_a_car_by_a_parking_boy_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        ParkingTicket ticket = client.parkByNewParkingBoy(newParkingBoy);
        Car fetched = client.fetchByNewParkingBoy(newParkingBoy,ticket);

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
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        ParkingTicket ticket_1 = client_1.parkByNewParkingBoy(newParkingBoy);
        Car fetched_1 = client_1.fetchByNewParkingBoy(newParkingBoy,ticket_1);

        ParkingTicket ticket_2 = client_2.parkByNewParkingBoy(newParkingBoy);
        Car fetched_2 = client_2.fetchByNewParkingBoy(newParkingBoy,ticket_2);

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
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = client.parkByNewParkingBoy(newParkingBoy);

        assertNull(client.fetchByNewParkingBoy(newParkingBoy,wrongTicket));
        assertSame(car, client.fetchByNewParkingBoy(newParkingBoy,ticket));
    }

    @Test
    void should_client_query_message_once_the_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        client.fetchByNewParkingBoy(newParkingBoy,wrongTicket);
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
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        client.fetchByNewParkingBoy(newParkingBoy,null);
        String message = client.getLastErrorMessage();

        assertEquals("Please provide your parking ticket.", message);
    }


    @Test
    void should_client_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.parkByNewParkingBoy(newParkingBoy);
        client.setCar(new Car());
        client.parkByNewParkingBoy(newParkingBoy);
        client.setCar(new Car());

        assertNull(client.parkByNewParkingBoy(newParkingBoy));
    }

    @Test
    void should_client_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        NewParkingBoy newParkingBoy = new NewParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.parkByNewParkingBoy(newParkingBoy);
        client.setCar(new Car());
        client.parkByNewParkingBoy(newParkingBoy);
        client.setCar(new Car());
        client.parkByNewParkingBoy(newParkingBoy);

        assertEquals("The parking lot is full.", client.getLastErrorMessage());
    }
}
