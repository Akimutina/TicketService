package ru.netology.ticketsService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketsManagerTest {
    TicketsRepository repo = new TicketsRepository();
    TicketsManager manager = new TicketsManager(repo);

    Tickets ticket1 = new Tickets(1, 5_000, "DME", "LED", 69);
    Tickets ticket2 = new Tickets(12, 4_000, "GOJ", "DME", 63);
    Tickets ticket3 = new Tickets(13, 4_500, "DME", "LED", 69);
    Tickets ticket4 = new Tickets(14, 9_000, "OVB", "KZN", 183);
    Tickets ticket5 = new Tickets(15, 12_000, "OVB", "LED", 258);
    Tickets ticket6 = new Tickets(16, 5_000, "DME", "LED", 69);
    Tickets ticket7 = new Tickets(17, 2_000, "DME", "LED", 69);
    Tickets ticket8 = new Tickets(18, 9_500, "OVB", "KZN", 183);

    @BeforeEach
    void setup() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);
    }

    @Test
    public void testSortTickets() {
        Tickets[] expected = new Tickets[]{ticket7, ticket3, ticket1, ticket6};
        Tickets[] actual = manager.findAll("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeTicketsByID() {

        manager.removeById(14);

        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket3, ticket5, ticket6, ticket7, ticket8};
        Tickets[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addTicketsAndGetIts() {

        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8};
        Tickets[] actual = repo.getTickets();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findMultipleTickets() {

        Tickets[] expected = new Tickets[]{ticket4, ticket8};
        Tickets[] actual = manager.findAll("OVB", "KZN");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneTickets() {

        Tickets[] expected = new Tickets[]{ticket2};
        Tickets[] actual = manager.findAll("GOJ", "DME");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void notFindNonexistentTickets() {

        Tickets[] expected = new Tickets[]{};
        Tickets[] actual = manager.findAll("GOJ", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

}
