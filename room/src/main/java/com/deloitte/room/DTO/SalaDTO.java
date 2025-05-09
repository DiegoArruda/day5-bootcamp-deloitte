package com.deloitte.room.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SalaDTO {
    @NotBlank
    private String nome;
    private int capacidade;
    private String localizacao;
}
