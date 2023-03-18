package ru.netology.ticketsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class TicketsRepositoryTest {

    private TicketsRepository repo = new TicketsRepository();
    private TicketsManager manager = new TicketsManager(repo);

    Tickets ticket1 = new Tickets(1, 5_000, "DME", "LED", 69);
    Tickets ticket2 = new Tickets(12, 4_000, "GOJ", "DME", 63);
    Tickets ticket3 = new Tickets(13, 3_000, "FUF", "KZN", 55);
    Tickets ticket4 = new Tickets(14, 9_000, "OVB", "KZN", 183);
    Tickets ticket5 = new Tickets(15, 12_000, "OVB", "LED", 258);

    @BeforeEach
    void setup() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
    }

    @Test
    public void addTicketsAndFindAll() {
        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket3, ticket4, ticket5};
        Tickets[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findById() {

        Tickets expected = ticket2;
        Tickets actual = repo.findById(12);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByUnCorrectId() {

        Tickets actual = repo.findById(23);

        Assertions.assertEquals(null, actual);
    }

    @Test
    public void removeByExistingId() {
        repo.removeById(13);

        Tickets[] expected = new Tickets[]{ticket1, ticket2, ticket4, ticket5};
        Tickets[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByNonExistingId() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(69);
        });
    }

    @Test
    public void doNotAddAnExistingId() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(ticket3);
        });
    }
}
