package com.g7.micro.repositorio;

import com.g7.micro.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByUsuarioAndPass(String usuario, String pass);
}
