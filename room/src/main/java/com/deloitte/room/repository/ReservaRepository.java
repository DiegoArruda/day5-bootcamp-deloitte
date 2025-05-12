package com.deloitte.room.repository;

import com.deloitte.room.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    List<Reserva> findBySalaIDAndInicioReservaLessThanEqualAndFimReservaGreaterThanEqual(Long salaId, LocalDateTime fim, LocalDateTime inicio);

    List<Reserva> findByInicioReservaAfterOrderByInicioReservaAsc(LocalDateTime dataHora);

    List<Reserva> findBySalaIDOrderByInicioReservaAsc(Long id);
}
