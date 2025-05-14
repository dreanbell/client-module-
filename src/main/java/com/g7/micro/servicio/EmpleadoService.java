package com.g7.micro.servicio;

import com.g7.micro.modelo.Empleado;
import com.g7.micro.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado validarCredenciales(String usuario, String pass) {
        return null;
    }

    public Optional<Empleado> autenticar(String usuario, String pass) {
        return empleadoRepository.findByUsuarioAndPass(usuario, pass);
    }

}
