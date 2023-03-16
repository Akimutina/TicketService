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

    @BeforeEach
    void setup() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
    }

    @Test
    public void testSortTickets() {
        Tickets[] expected = new Tickets[]{ticket3, ticket1};
        Tickets[] actual = manager.findAll("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeTickets() {

        manager.removeById(14);

        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket3, ticket5};
        Tickets[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addTicketsAndGetIts() {

        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket3, ticket4, ticket5};
        Tickets[] actual = repo.getTickets();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findMultipleTickets() {

        Tickets[] expected = new Tickets[]{ticket3, ticket1};
        Tickets[] actual = manager.findAll("DME", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findOneTickets() {

        Tickets[] expected = new Tickets[]{ticket2};
        Tickets[] actual = manager.findAll("GOJ", "DME");

        Assertions.assertArrayEquals(expected, actual);
    }

}
