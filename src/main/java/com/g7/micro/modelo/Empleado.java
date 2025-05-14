package com.g7.micro.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column(name = "id_empl")
    private Integer idEmpl;

    @Column(name = "usuario", nullable = false, length = 24)
    private String usuario;

    @Column(name = "pass", nullable = false, length = 15)
    private String pass;

    @Column(name = "email")
    private String email;

    // Getters y Setters
    public Integer getIdEmpl() { return idEmpl; }
    public void setIdEmpl(Integer idEmpl) { this.idEmpl = idEmpl; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
