package com.g7.micro.servicio;

import com.g7.micro.modelo.Empleado;
import com.g7.micro.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Optional<Empleado> login(String usuario, String password) {
        return empleadoRepository.findByUsuarioAndPass(usuario, password);
    }
}
