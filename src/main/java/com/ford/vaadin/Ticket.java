package com.ford.vaadin;

public class Ticket {
    private long id;
    private String cliente;
    private String asunto;
    private String descripcion;
    private String estado;
    private String respuesta;

    public Ticket(long id, String cliente, String asunto, String descripcion, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.respuesta = null;}

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
