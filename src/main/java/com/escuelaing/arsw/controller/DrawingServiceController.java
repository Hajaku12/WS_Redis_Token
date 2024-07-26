package com.escuelaing.arsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.escuelaing.arsw.repository.TicketRepository;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class DrawingServiceController {
    TicketRepository ticketRepo = new TicketRepository();
    
     /**
     * Returns the status of the server.
     *
     * @return a JSON string indicating the server status
     */
    @RequestMapping(
            value = "/status",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public String status() {
        return "{\"status\":\"Greetings from Spring Boot. "
                + LocalDate.now() + ", "
                + LocalTime.now()
                + ". " + "The server is Runnig!\"}";
    }
    
     /**
     * Generates and returns a new ticket.
     *
     * @return a JSON string containing the generated ticket
     */
    @GetMapping("/getticket")

     public String getTicket() {

        return "{\"ticket\":\"" +
     ticketRepo.getTicket() + "\"}";
 }
}
