package com.ford.vaadin;

import java.time.LocalDateTime;

public class Ticket {
    private long id;
    private String nombreCliente;
    private String correoCliente;
    private String asunto;
    private String descripcion;
    private String estado;
    private String respuesta;
    private LocalDateTime fechaCreacion;

    public Ticket(long id, String nombreCliente, String correoCliente, String asunto,String descripcion, String respuesta, String estado) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }

        if (correoCliente == null || !correoCliente.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Correo electrónico no válido: " + correoCliente);
        }

        if (asunto == null || asunto.isBlank()) {
            throw new IllegalArgumentException("El asunto no puede estar vacío");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }

        if (estado == null || (!estado.equalsIgnoreCase("Abierto") &&
                !estado.equalsIgnoreCase("En proceso") &&
                !estado.equalsIgnoreCase("Cerrado"))) {
            throw new IllegalArgumentException("Estado no válido: " + estado);
        }
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.respuesta = respuesta;
        this.estado = estado;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getAsunto(){
        return asunto;
    }

    public String getEstado() {
        return estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // Setters necesarios para cambiar estado y respuesta
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
