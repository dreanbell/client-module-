package com.g7.micro.vista;

import com.g7.micro.modelo.Ticket;
import com.g7.micro.servicio.TicketService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

import java.util.List;

@Route("responder/:idEmpleado")
public class ResponderTicketView extends VerticalLayout implements BeforeEnterObserver {

    private final TicketService ticketService;
    private int idEmpleado;

    private Grid<Ticket> ticketGrid;
    private TextArea respuestaField;
    private Button responderBtn;

    public ResponderTicketView(TicketService ticketService) {
        this.ticketService = ticketService;
        configurarVista();
    }

    private void configurarVista() {
        setSpacing(true);
        setPadding(true);

        add(new H2("Responder Tickets"));

        ticketGrid = new Grid<>(Ticket.class);
        ticketGrid.setColumns("id", "idCl", "fechaCreateTicket", "asunto", "descrpcion", "estado");
        ticketGrid.setWidthFull();

        respuestaField = new TextArea("Respuesta al ticket");
        respuestaField.setWidthFull();

        responderBtn = new Button("Enviar respuesta");
        responderBtn.setEnabled(false);

        add(ticketGrid, respuestaField, responderBtn);

        ticketGrid.asSingleSelect().addValueChangeListener(event -> {
            Ticket seleccionado = event.getValue();
            responderBtn.setEnabled(seleccionado != null);
        });

        responderBtn.addClickListener(e -> {
            Ticket seleccionado = ticketGrid.asSingleSelect().getValue();
            if (seleccionado != null && !respuestaField.getValue().trim().isEmpty()) {
                ticketService.responderTicket(seleccionado.getId(), respuestaField.getValue(), idEmpleado);
                Notification.show("Respuesta enviada correctamente", 3000, Notification.Position.MIDDLE);
                respuestaField.clear();
                actualizarListaTickets();
            } else {
                Notification.show("Debe seleccionar un ticket y escribir una respuesta");
            }
        });
    }

    private void actualizarListaTickets() {
        List<Ticket> tickets = ticketService.obtenerTodos();
        ticketGrid.setItems(tickets);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        RouteParameters parameters = event.getRouteParameters();
        parameters.get("idEmpleado").ifPresent(id -> {
            try {
                idEmpleado = Integer.parseInt(id);
                actualizarListaTickets();
            } catch (NumberFormatException ex) {
                Notification.show("ID de empleado inválido");
                UI.getCurrent().navigate("");
            }
        });
    }
}
