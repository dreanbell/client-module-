package com.ford.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")  // Esto hace que esta sea la vista predeterminada al abrir la aplicación
public class TicketView extends VerticalLayout {

    private TicketServicio ticketServicio = TicketServicio.getInstancia();

    public TicketView() {
        TextField clienteField = new TextField("Cliente");
        TextField asuntoField = new TextField("Asunto");
        TextField descripcionField = new TextField("Descripción");

        Button submitButton = new Button("Registrar Solicitud");

        submitButton.addClickListener(e -> {
            // Crear un nuevo ticket
            Ticket nuevoTicket = new Ticket(0, clienteField.getValue(), asuntoField.getValue(), descripcionField.getValue(), "Abierto");
            ticketServicio.obtenerTickets().add(nuevoTicket); // Agregar el nuevo ticket a la lista de tickets

            Notification.show("Solicitud registrada con ID: " + nuevoTicket.getId()); // Usar getId() de Ticket
        });

        add(clienteField, asuntoField, descripcionField, submitButton);
    }
}
