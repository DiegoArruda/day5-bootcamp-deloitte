package com.deloitte.room.DTO;



import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReservaDTO {
    private Long salaId;
    private String nomeResponsavel;
    private LocalDateTime inicioReserva;
    private LocalDateTime fimReserva;
}
