package com.deloitte.room.repositories;

import com.deloitte.room.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findBySalaIdAndInicioReservaLessThanEqualAndFimReservaGreaterThanEqual(Long salaId, LocalDateTime fim, LocalDateTime inicio);

    List<Reserva> findByInicioReservaAfterOrderByInicioReservaAsc(LocalDateTime dataHora);

    List<Reserva> findBydSalaIdOrderByInicioReservaAsc(Long id);
}
