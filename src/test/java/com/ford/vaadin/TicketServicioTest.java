package com.ford.vaadin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TicketServicioTest {

    private TicketServicio ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketServicio(
            "Ana Torres",
            "ana.torres@example.com",
            "Falla en sensores de reversa",
            "Los sensores de reversa emiten alertas sin obstáculos."
        );
    }

    @Test
    void testCreacionDeTicket() {
        assertNotNull(ticket);
    }

    @Test
    void testIdUnicoGenerado() {
        assertNotNull(ticket.getIdTicket());
        assertTrue(ticket.getIdTicket().startsWith("TICKET-"));
    }

    @Test
    void testDatosDelCliente() {
        assertEquals("Ana Torres", ticket.getNombreCliente());
        assertEquals("ana.torres@example.com", ticket.getCorreoCliente());
    }

    @Test
    void testEstadoInicial() {
        assertEquals("Abierto", ticket.getEstado());
    }

    @Test
    void testFechaCreacion() {
        assertNotNull(ticket.getFechaCreacion());
        assertTrue(ticket.getFechaCreacion().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
