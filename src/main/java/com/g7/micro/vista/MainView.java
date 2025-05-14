package com.g7.micro.vista;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Button loginBtn = new Button("Login", e -> login());
        Button ticketBtn = new Button("Crear Ticket", e -> crearTicket());

        add(new H1("Centro de Soporte"), loginBtn, ticketBtn);
    }

    private void login() {
        getUI().ifPresent(ui -> ui.navigate("login"));
    }

    private void crearTicket() {
        getUI().ifPresent(ui -> ui.navigate("ticket"));
    }
}
