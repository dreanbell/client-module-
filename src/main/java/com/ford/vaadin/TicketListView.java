package com.ford.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route("tickets")
public class TicketListView extends VerticalLayout {

    private TicketServicio ticketServicio = TicketServicio.getInstancia();
    private Grid<Ticket> grid = new Grid<>(Ticket.class);
    private TextArea respuestaArea = new TextArea("Respuesta");
    private Button responderBtn = new Button("Enviar Respuesta");

    public TicketListView() {
        actualizarGrid();

        grid.setColumns("cliente", "asunto", "descripcion", "estado", "respuesta");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

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

        responderBtn.addClickListener(e -> {
            Ticket seleccionado = grid.asSingleSelect().getValue();
            if (seleccionado != null) {
                ticketServicio.responderTicket(seleccionado.getId(), respuestaArea.getValue());
                actualizarGrid();
            }
        });

        responderBtn.setEnabled(false);

        add(grid, respuestaArea, responderBtn);
    }

    private void actualizarGrid() {
        List<Ticket> tickets = ticketServicio.obtenerTickets();
        grid.setItems(tickets);
    }
}
