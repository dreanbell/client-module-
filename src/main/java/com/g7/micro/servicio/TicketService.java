package com.g7.micro.servicio;

import com.g7.micro.modelo.Ticket;
import com.g7.micro.repositorio.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CorreoService correoService; // ✅ Agregar esto

    public Ticket crearTicket(Ticket ticket) {
        ticket.setFechaCreateTicket(LocalDate.now());
        ticket.setEstado("Pendiente");
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTodos() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> obtenerPorId(int id) {
        return ticketRepository.findById(id);
    }

    public Ticket responderTicket(int id, String respuesta, int idEmpleado) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setRespuesta(respuesta);
            ticket.setIdEmple(idEmpleado);
            ticket.setEstado("Respondido");
            Ticket actualizado = ticketRepository.save(ticket);

            // ✅ Enviar correo al cliente
            correoService.enviarCorreo(
                    ticket.getEmailCl(),
                    "Respuesta a tu ticket: " + ticket.getAsunto(),
                    "Hola,\n\nTu ticket ha sido respondido:\n\n" +
                            "Asunto: " + ticket.getAsunto() + "\n" +
                            "Respuesta: " + respuesta + "\n\n" +
                            "Gracias por contactarnos."
            );

            return actualizado;
        }
        return null;
    }
}
