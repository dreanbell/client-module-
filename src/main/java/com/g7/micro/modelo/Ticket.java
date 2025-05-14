package com.g7.micro.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDate;


@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_create_ticket", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaCreateTicket;

    @Column(name = "asunto", nullable = false)
    private String asunto;

    @Column(name = "descrpcion")
    private String descrpcion;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "id_cl", nullable = false)
    private Integer idCl;

    @Column(name = "id_emple", nullable = false)
    private Integer idEmple;

    @Column(name = "email_cl", nullable = false)
    private String emailCl;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getFechaCreateTicket() {
        return fechaCreateTicket;
    }

    public void setFechaCreateTicket(LocalDate fechaCreateTicket) {
        this.fechaCreateTicket = fechaCreateTicket;
    }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getDescrpcion() { return descrpcion; }
    public void setDescrpcion(String descrpcion) { this.descrpcion = descrpcion; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getIdCl() { return idCl; }
    public void setIdCl(Integer idCl) { this.idCl = idCl; }

    public Integer getIdEmple() { return idEmple; }
    public void setIdEmple(Integer idEmple) { this.idEmple = idEmple; }

    public String getEmailCl() {
        return emailCl;
    }

    public void setEmailCl(String emailCl) {
        this.emailCl = emailCl;
    }
}
