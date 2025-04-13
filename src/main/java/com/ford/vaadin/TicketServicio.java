package com.ford.vaadin;

import java.util.ArrayList;
import java.util.List;

public class TicketServicio {

    private static TicketServicio instancia;
    private List<Ticket> tickets;

    private TicketServicio() {
        tickets = new ArrayList<>();
        // Cargar algunos tickets de ejemplo
        tickets.add(new Ticket(1, "Juan Pérez", "juanperez@gmail.com","Problema con el vehículo", "suena cuando suben las rpm","", "Abierto"));
        tickets.add(new Ticket(2, "María López", "marilopez@gmail.com", "Consulta sobre repuestos", "busco repuestos del diferencial","", "Cerrado"));
    }

    public static TicketServicio getInstancia() {
        if (instancia == null) {
            instancia = new TicketServicio();
        }
        return instancia;
    }

    public List<Ticket> obtenerTickets() {
        return tickets;
    }

    public void responderTicket(long id, String respuesta) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                ticket.setRespuesta(respuesta);
                ticket.setEstado("Cerrado");
                break;
            }
        }
    }
}
