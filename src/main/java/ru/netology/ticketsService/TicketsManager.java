package ru.netology.ticketsService;

import java.util.Arrays;

public class TicketsManager {
    private TicketsRepository repo;

    public TicketsManager(TicketsRepository repo) {
        this.repo = repo;
    }

    public void save(Tickets items) {
        repo.save(items);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Tickets[] findAll(String departureAirport, String arrivalAirport) {
        Tickets[] result = new Tickets[0];
        for (Tickets tickets : repo.findAll()) {
            if (matches(tickets, departureAirport, arrivalAirport)) {
                Tickets[] tmp = new Tickets[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[result.length] = tickets;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public boolean matches(Tickets ticket, String searchFrom, String searchTo) {
        if (ticket.matches(searchFrom, searchTo)) {
            return true;
        } else {
            return false;
        }
    }
}


