package com.deloitte.room.controller.dto;

import com.deloitte.room.model.Sala;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


public record SalaDTO(Long id, String nome, int capacidade, String localizacao) {

    public static SalaDTO forCreation(String nome, int capacidade, String localizacao){
        return new SalaDTO(null,nome, capacidade, localizacao);
    }
    public static SalaDTO fromEntity(Sala sala) {
        return new SalaDTO(
                sala.getID(),
                sala.getNome(),
                sala.getCapacidadeMaxima(),
                sala.getLocalizacao()
        );
    }
}
