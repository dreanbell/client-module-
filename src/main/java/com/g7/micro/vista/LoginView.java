package com.g7.micro.vista;
import com.g7.micro.modelo.Empleado;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Route("login")
public class LoginView extends VerticalLayout {

    private final RestTemplate restTemplate = new RestTemplate();

    public LoginView() {
        setSpacing(true);
        setPadding(true);

        add(new H2("Inicio de Sesión de Empleado"));

        TextField usuarioField = new TextField("Usuario");
        PasswordField passwordField = new PasswordField("Contraseña");

        Button loginButton = new Button("Iniciar sesión");

        add(usuarioField, passwordField, loginButton);

        loginButton.addClickListener(e -> {
            String usuario = usuarioField.getValue();
            String password = passwordField.getValue();

            String url = "http://localhost:8080/api/login";

            // Cuerpo de la petición JSON
            Map<String, String> loginBody = new HashMap<>();
            loginBody.put("usuario", usuario);
            loginBody.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(loginBody, headers);

            try {
                ResponseEntity<Empleado> response = restTemplate.postForEntity(url, request, Empleado.class);

                Empleado empleado = response.getBody();

                if (empleado != null) {
                    Notification.show("Inicio de sesión exitoso");
                    UI.getCurrent().navigate("responder/" + empleado.getIdEmpl());
                } else {
                    Notification.show("Error: Empleado no encontrado", 3000, Notification.Position.MIDDLE);
                }
            } catch (Exception ex) {
                Notification.show("Credenciales incorrectas o error de conexión", 3000, Notification.Position.MIDDLE);
                ex.printStackTrace();
            }
        });
    }
}
