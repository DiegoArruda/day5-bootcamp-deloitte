package com.deloitte.room.controller.dto;

import com.deloitte.room.model.Reserva;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

public record ReservaDTO(
        Long id,
        Long salaId,
        String nomeResponsavel,
        @FutureOrPresent(message = "Reserva não pode ser no passado")
        LocalDateTime inicioReserva,
        LocalDateTime fimReserva) {

    public static ReservaDTO forCreation(Long salaId, String nomeResponsavel, LocalDateTime inicioReserva, LocalDateTime
            fimReserva) {
        return new ReservaDTO(null, salaId, nomeResponsavel, inicioReserva, fimReserva);
    }

    public static ReservaDTO fromEntity(Reserva reserva) {

        return new ReservaDTO(
                reserva.getID(),
                reserva.getSala().getID(),
                reserva.getNomeResponsavel(),
                reserva.getInicioReserva(),
                reserva.getFimReserva()
        );
    }

}
