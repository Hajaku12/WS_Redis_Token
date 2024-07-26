package com.escuelaing.arsw.endpoints;

import java.io.IOException;
import java.util.logging.Level;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import com.escuelaing.arsw.repository.TicketRepository;
import com.escuelaing.arsw.redis.BBAplicationContext;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
@Component
@ServerEndpoint("/bbService")

public class BBEndpoint {
    private boolean accepted = false;
    private static final Logger logger = Logger.getLogger(BBEndpoint.class.getName());
    /* Queue for all open WebSocket sessions */
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    private Session ownSession = null;
    TicketRepository ticketRepo =
            (TicketRepository)
                    BBAplicationContext.getApplicationContext().getBean("ticketRepository");

   /**
     * Sends a message to all clients except the sender.
     *
     * @param msg the message to send
     */
    public void send(String msg) {
        try {
            /* Send updates to all open WebSocket sessions */
            for (Session session : queue) {

                if (!session.equals(this.ownSession)) {
                    session.getBasicRemote().sendText(msg);
                }
                logger.log(Level.INFO, "Sent: {0}", msg);
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    /**
     * Processes incoming messages from clients.
     *
     * @param message the message received from the client
     * @param session the WebSocket session of the client
     */
    @OnMessage
    public void processPoint(String message, Session session) {
        if (accepted) {
            this.send(message);
        } else {
            if (!accepted && ticketRepo.checkTicket(message)) {
                accepted = true;
            }else{
                try {
                    ownSession.close();
                } catch (IOException ex) {
                    Logger.getLogger(BBEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

     /**
     * Handles the opening of a new WebSocket connection.
     *
     * @param session the WebSocket session that was opened
     */
    @OnOpen
    public void openConnection(Session session) {
        /* Register this connection in the queue */
        queue.add(session);
        ownSession = session;
        logger.log(Level.INFO, "Connection opened.");
        try {
            session.getBasicRemote().sendText("Connection established.");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    @OnClose
    public void closedConnection(Session session) {
    /**
     * Handles the closing of a WebSocket connection.
     *
     * @param session the WebSocket session that was closed
     */
    /* Remove this connection from the queue */
    queue.remove(session);
    logger.log(Level.INFO, "Connection closed.");
}
    @OnError
    public void error(Session session, Throwable t) {
    /**
     * Handles errors that occur during the WebSocket connection.
     *
     * @param session the WebSocket session that encountered the error
     * @param t the throwable error that occurred
     */
    /* Remove this connection from the queue */
    queue.remove(session);
    logger.log(Level.INFO, t.toString());
    logger.log(Level.INFO, "Connection error.");
}
}