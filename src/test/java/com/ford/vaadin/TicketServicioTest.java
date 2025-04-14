package com.ford.vaadin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServicioTest {

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket(1, "Ana Torres","anatorres@gmai.com"
                ,"Problema con el radiador", "no arranca cuando la temperatura sube"
                , "","Abierto");
    }

    @Test
    void testCreacionDeTicket() {
        assertNotNull(ticket);
    }

    @Test
    void testIdGenerado() {
        assertEquals(1, ticket.getId());
    }

    @Test
    void testDatosDelCliente() {
        assertEquals("Ana Torres", ticket.getNombreCliente());
        assertEquals("anatorres@gmai.com", ticket.getCorreoCliente()); // según la clase Ticket
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
    @Test
    void testAsuntoVacioLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1L, "Pedro", "pedro@mail.com", "", "Descripción", "Respuesta", "Abierto");
        });
        assertEquals("El asunto no puede estar vacío", exception.getMessage());
    }

    @Test
    void testDescripcionNulaLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1L, "Ana", "ana@mail.com", "Asunto", null, "Respuesta", "Abierto");
        });
        assertEquals("La descripción no puede estar vacía", exception.getMessage());
    }

    @Test
    void testEstadoInvalidoLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1L, "Luis", "luis@mail.com", "Asunto", "Descripción", "Respuesta", "Invalido");
        });
        assertEquals("Estado no válido: Invalido", exception.getMessage());
    }

}
