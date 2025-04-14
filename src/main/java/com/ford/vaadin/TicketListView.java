package com.ford.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("tickets")
public class TicketListView extends VerticalLayout {

    private TicketServicio ticketServicio = TicketServicio.getInstancia();
    private Grid<Ticket> grid = new Grid<>(Ticket.class);
    private TextArea respuestaArea = new TextArea("Respuesta");
    private Button responderBtn = new Button("Enviar Respuesta");

    public TicketListView() {
        // Título de la vista
        Label titulo = new Label("Gestión de Solicitudes de Servicio");
        titulo.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("margin-bottom", "20px");

        // Configuración del grid
        actualizarGrid();
        grid.removeAllColumns();
        grid.addColumn(Ticket::getId).setHeader("ID");
        grid.addColumn(Ticket::getNombreCliente).setHeader("Cliente");
        grid.addColumn(Ticket::getCorreoCliente).setHeader("Correo");
        grid.addColumn(Ticket::getAsunto).setHeader("Asunto");
        grid.addColumn(Ticket::getDescripcion).setHeader("Descripción");
        grid.addColumn(Ticket::getEstado).setHeader("Estado");
        grid.addColumn(Ticket::getRespuesta).setHeader("Respuesta");
        grid.addColumn(Ticket::getFechaCreacion).setHeader("Fecha de Creación");

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setWidthFull();

        // Configuración de área de respuesta
        respuestaArea.setWidth("100%");
        respuestaArea.setPlaceholder("Escribe la respuesta aquí...");
        respuestaArea.setHeight("150px");

        // Evento al seleccionar un ticket
        grid.asSingleSelect().addValueChangeListener(event -> {
            Ticket seleccionado = event.getValue();
            if (seleccionado != null) {
                respuestaArea.setValue(seleccionado.getRespuesta() != null ? seleccionado.getRespuesta() : "");
                responderBtn.setEnabled(true);
            } else {
                respuestaArea.clear();
                responderBtn.setEnabled(false);
            }
        });

        // Acción del botón de responder
        responderBtn.addClickListener(e -> {
            Ticket seleccionado = grid.asSingleSelect().getValue();
            if (seleccionado != null) {
                ticketServicio.responderTicket(seleccionado.getId(), respuestaArea.getValue());
                actualizarGrid();
            }
        });

        responderBtn.setEnabled(false);
        responderBtn.getStyle().set("margin-top", "10px");

        // Layout para la respuesta y el botón
        VerticalLayout respuestaLayout = new VerticalLayout(respuestaArea, responderBtn);
        respuestaLayout.setWidth("100%");
        respuestaLayout.setSpacing(true);
        respuestaLayout.setPadding(false);

        // Layout principal
        setAlignItems(Alignment.CENTER);
        setWidth("90%");
        setSpacing(true);
        setPadding(true);

        add(titulo, grid, respuestaLayout);
    }

    private void actualizarGrid() {
        List<Ticket> tickets = ticketServicio.obtenerTickets();
        grid.setItems(tickets);
    }
}
