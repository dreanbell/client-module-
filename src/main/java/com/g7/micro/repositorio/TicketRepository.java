package com.g7.micro.repositorio;

import com.g7.micro.modelo.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByEstado(String estado);
}
