package com.g7.micro.vista;

import com.g7.micro.modelo.Ticket;
import com.g7.micro.servicio.TicketService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("ticket")
public class TicketView extends VerticalLayout {

    private final TicketService ticketService;

    public TicketView(TicketService ticketService) {
        this.ticketService = ticketService;

        setSpacing(true);
        setPadding(true);

        add(new H2("Crear Ticket de Servicio"));

        // Campos del formulario
        TextField idCliente = new TextField("ID del Cliente");
        TextField EmailCliente = new TextField("Email del Cliente");
        TextField asunto = new TextField("Asunto");
        TextArea descripcion = new TextArea("Descripción");

        DatePicker fecha = new DatePicker("Fecha de creación");
        fecha.setValue(LocalDate.now()); // Fecha automática

        Button enviar = new Button("Enviar Ticket");

        // Layout
        add(idCliente,EmailCliente, asunto, descripcion, fecha, enviar);

        // Acción al enviar
        enviar.addClickListener(e -> {
            try {
                int idCl = Integer.parseInt(idCliente.getValue());
                String asuntoTxt = asunto.getValue();
                String desc = descripcion.getValue();
                String email =  EmailCliente.getValue();

                Ticket ticket = new Ticket();
                ticket.setEmailCl(email);
                ticket.setIdCl(idCl);
                ticket.setAsunto(asuntoTxt);
                ticket.setDescrpcion(desc);
                ticket.setFechaCreateTicket(fecha.getValue());
                ticket.setEstado("Pendiente");
                ticket.setIdEmple(0); // Aún no asignado

                ticketService.crearTicket(ticket);
                Notification.show("Ticket creado exitosamente", 3000, Notification.Position.MIDDLE);

                // Limpiar campos
                idCliente.clear();
                asunto.clear();
                descripcion.clear();
                fecha.setValue(LocalDate.now());

            } catch (NumberFormatException ex) {
                Notification.show("El ID del cliente debe ser numérico", 3000, Notification.Position.MIDDLE);
            } catch (Exception ex) {
                Notification.show("Error al crear el ticket: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });
    }
}
