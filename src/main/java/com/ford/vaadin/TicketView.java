package com.ford.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")  // Vista predeterminada
public class TicketView extends VerticalLayout {

    private TicketServicio ticketServicio = TicketServicio.getInstancia();

    public TicketView() {
        // Títulos
        Label titulo = new Label("Bienvenido al módulo de atención al cliente");
        titulo.getStyle().set("font-size", "24px").set("font-weight", "bold");

        Label subtitulo = new Label("¿Deseas registrar una solicitud de servicio?");
        subtitulo.getStyle().set("font-size", "18px").set("margin-bottom", "20px");

        // Campos del formulario
        TextField nombreCliente = new TextField("Nombre");
        TextField correoCliente = new TextField("Correo Cliente");
        TextField asuntoCliente = new TextField("Asunto Cliente");
        TextField descripcionField = new TextField("Descripción");

        Button submitButton = new Button("Registrar Solicitud");

        submitButton.addClickListener(e -> {
            Ticket nuevoTicket = new Ticket(0,nombreCliente.getValue(),correoCliente.getValue(),asuntoCliente.getValue(),descripcionField.getValue(),"","Abierto");
            ticketServicio.obtenerTickets().add(nuevoTicket);
            Notification.show("Solicitud registrada con ID: " + nuevoTicket.getId());
        });

        // Alinear y agregar elementos
        setAlignItems(Alignment.CENTER); // Centra todos los elementos
        add(titulo, subtitulo, nombreCliente, correoCliente, asuntoCliente, descripcionField, submitButton);
    }
}
