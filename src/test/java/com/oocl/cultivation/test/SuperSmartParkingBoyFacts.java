package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyFacts {

    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = superSmartParkingBoy.park(car);
        Car fetched = superSmartParkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = superSmartParkingBoy.park(firstCar);
        ParkingTicket secondTicket = superSmartParkingBoy.park(secondCar);

        Car fetchedByFirstTicket = superSmartParkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = superSmartParkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = superSmartParkingBoy.park(car);

        assertNull(superSmartParkingBoy.fetch(wrongTicket));
        assertSame(car, superSmartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        superSmartParkingBoy.fetch(wrongTicket);
        String message = superSmartParkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        superSmartParkingBoy.fetch(wrongTicket);
        assertNotNull(superSmartParkingBoy.getLastErrorMessage());

        ParkingTicket ticket = superSmartParkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(superSmartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = superSmartParkingBoy.park(car);

        assertNull(superSmartParkingBoy.fetch(null));
        assertSame(car, superSmartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.fetch(null);

        assertEquals(
                "Please provide your parking ticket.",
                superSmartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(ticket);

        assertNull(superSmartParkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        ParkingTicket ticket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(ticket);
        superSmartParkingBoy.fetch(ticket);

        assertEquals(
                "Unrecognized parking ticket.",
                superSmartParkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_get_message_if_parkingLots_are_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());


        assertEquals(null, superSmartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());

        assertNull(superSmartParkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_parkingLots_are_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());


        assertEquals("The parking lot is full.", superSmartParkingBoy.getLastErrorMessage());
    }

    @Test
    void should_park_a_car_by_a_parking_boy_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        ParkingTicket ticket = client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        Car fetched = client.fetchBySuperSmartParkingBoy(superSmartParkingBoy,ticket);

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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        ParkingTicket ticket_1 = client_1.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        Car fetched_1 = client_1.fetchBySuperSmartParkingBoy(superSmartParkingBoy,ticket_1);

        ParkingTicket ticket_2 = client_2.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        Car fetched_2 = client_2.fetchBySuperSmartParkingBoy(superSmartParkingBoy,ticket_2);

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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = client.parkBySuperSmartParkingBoy(superSmartParkingBoy);

        assertNull(client.fetchBySuperSmartParkingBoy(superSmartParkingBoy,wrongTicket));
        assertSame(car, client.fetchBySuperSmartParkingBoy(superSmartParkingBoy,ticket));
    }

    @Test
    void should_client_query_message_once_the_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot_1 = new ParkingLot();
        ParkingLot parkingLot_2 = new ParkingLot();
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket wrongTicket = new ParkingTicket();

        client.fetchBySuperSmartParkingBoy(superSmartParkingBoy,wrongTicket);
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        client.fetchBySuperSmartParkingBoy(superSmartParkingBoy,null);
        String message = client.getLastErrorMessage();

        assertEquals("Please provide your parking ticket.", message);
    }

    @Test
    void should_client_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        client.setCar(new Car());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        client.setCar(new Car());

        assertNull(client.parkBySuperSmartParkingBoy(superSmartParkingBoy));
    }

    @Test
    void should_client_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot_1 = new ParkingLot(capacity);
        ParkingLot parkingLot_2 = new ParkingLot(capacity);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        client.setCar(new Car());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        client.setCar(new Car());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);

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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        assertEquals(parkingLot_3,superSmartParkingBoy.chooseParkingLot());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        assertEquals(2, parkingLot_3.getAvailableParkingPosition());
    }

    @Test
    void should_client_park_multiply_cars_to_most_park_space_parking_lot() {
        final int capacity_1 = 1;
        final int capacity_2 = 3;
        ParkingLot parkingLot_1 = new ParkingLot(capacity_1);
        ParkingLot parkingLot_2 = new ParkingLot(capacity_2);
        List<ParkingLot> parkingLots =  Arrays.asList(parkingLot_1, parkingLot_2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Client client = new Client(new Car());
        assertEquals(parkingLot_2,superSmartParkingBoy.chooseParkingLot());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        assertEquals(2, parkingLot_2.getAvailableParkingPosition());

        client.setCar(new Car());
        assertEquals(parkingLot_1,superSmartParkingBoy.chooseParkingLot());
        client.parkBySuperSmartParkingBoy(superSmartParkingBoy);
        assertEquals(0, parkingLot_1.getAvailableParkingPosition());


    }

}
