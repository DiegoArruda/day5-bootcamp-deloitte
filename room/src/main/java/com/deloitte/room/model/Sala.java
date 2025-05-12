package com.deloitte.room.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    //rever anotação mappedBy
    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas = new ArrayList<>();


}
