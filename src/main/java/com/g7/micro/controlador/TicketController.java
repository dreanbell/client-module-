package com.g7.micro.controlador;

import com.g7.micro.modelo.Ticket;
import com.g7.micro.servicio.CorreoService;
import com.g7.micro.servicio.TicketService;
import com.g7.micro.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CorreoService correoService;

    @PostMapping
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return ticketService.crearTicket(ticket);
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketService.obtenerTodos();
    }

    @PostMapping("/responder")
    public Ticket responderTicket(@RequestParam int id,
                                  @RequestParam String respuesta,
                                  @RequestParam int idEmpleado) {
        Ticket ticket = ticketService.responderTicket(id, respuesta, idEmpleado);
        if (ticket != null) {
            clienteRepository.findById(ticket.getIdCl()).ifPresent(cliente -> {
                correoService.enviarCorreo(
                        cliente.getEmail(),
                        "Respuesta a su ticket",
                        "Respuesta: " + respuesta
                );
            });
        }
        return ticket;
    }
}
