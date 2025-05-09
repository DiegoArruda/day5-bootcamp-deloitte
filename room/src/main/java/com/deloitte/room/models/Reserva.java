package com.deloitte.room.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @ManyToOne
    private Sala sala;
    @NotBlank
    private String nomeResponsavel;
    @NotNull
    private LocalDateTime inicioReserva;
    @NotNull
    private LocalDateTime fimReserva;

}
