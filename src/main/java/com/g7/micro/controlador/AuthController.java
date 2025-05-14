package com.g7.micro.controlador;

import com.g7.micro.dto.LoginDTO;
import com.g7.micro.modelo.Empleado;
import com.g7.micro.servicio.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public Empleado login(@RequestBody LoginDTO loginDTO) {
        Optional<Empleado> empleado = authService.login(loginDTO.getUsuario(), loginDTO.getPassword());
        return empleado.orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }
}
