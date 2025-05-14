package com.g7.micro.controlador;

import com.g7.micro.modelo.Empleado;
import com.g7.micro.servicio.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/login")
    public Empleado login(@RequestParam String usuario, @RequestParam String pass) {
        Optional<Empleado> empleado = empleadoService.autenticar(usuario, pass);
        return empleado.orElse(null);
    }
}
