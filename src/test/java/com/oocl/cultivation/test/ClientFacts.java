package com.oocl.cultivation.test;


import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientFacts {
    @Test
    void should_park_a_car_by_a_parking_boy_and_get_it_back() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket = client.park(parkingBoy);
        Car fetched = client.fetch(parkingBoy,ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_by_a_parking_boy_and_get_it_back() {
        Car car_1 = new Car();
        Client client_1 = new Client(car_1);

        Car car_2 = new Car();
        Client client_2 = new Client(car_2);

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket_1 = client_1.park(parkingBoy);
        Car fetched_1 = client_1.fetch(parkingBoy,ticket_1);

        ParkingTicket ticket_2 = client_2.park(parkingBoy);
        Car fetched_2 = client_2.fetch(parkingBoy,ticket_2);

        assertSame(fetched_1, car_1);
        assertSame(fetched_2, car_2);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = client.park(parkingBoy);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, client.fetch(parkingBoy,ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        client.fetch(parkingBoy,wrongTicket);
        String message = client.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        Car car = new Car();
        Client client = new Client(car);
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        client.fetch(parkingBoy,null);
        String message = client.getLastErrorMessage();

        assertEquals("Please provide your parking ticket.", message);
    }


    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Client client = new Client(new Car());
        client.park(parkingBoy);
        client.setCar(new Car());

        assertNull(client.park(parkingBoy));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Client client = new Client(new Car());
        client.park(parkingBoy);
        client.setCar(new Car());
        client.park(parkingBoy);

        assertEquals("The parking lot is full.", client.getLastErrorMessage());
    }

}
