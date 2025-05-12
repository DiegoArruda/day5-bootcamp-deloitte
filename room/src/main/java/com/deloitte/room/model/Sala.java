package com.deloitte.room.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false, unique = true)
    private String nome;
    private int capacidadeMaxima;
    private String localizacao;


}
