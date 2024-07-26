package com.escuelaing.arsw.repository;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
@Component
public class TicketRepository {

    @Autowired
    private StringRedisTemplate template;

    // Inject the template as ListOperations
    @Resource(name = "stringRedisTemplate")
    private ListOperations<String, String> listTickets;

    private int ticketnumber;

    public TicketRepository() {
    }

    /**
     * Generates a new ticket number, stores it in Redis, and returns the ticket number.
     * 
     * @return the generated ticket number
     */
    public synchronized Integer getTicket() {
        Integer a = ticketnumber++;
        listTickets.leftPush("ticketStore", a.toString());
        return a;
    }

    /**
     * Checks if the provided ticket is valid by attempting to remove it from the Redis store.
     * 
     * @param ticket the ticket number to check
     * @return true if the ticket is valid and was removed, false otherwise
     */
    public boolean checkTicket(String ticket) {
        Long isValid = listTickets.getOperations().boundListOps("ticketStore").remove(0, ticket);
        return (isValid > 0l);
    }
}