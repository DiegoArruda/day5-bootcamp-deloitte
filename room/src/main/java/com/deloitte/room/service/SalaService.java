package com.deloitte.room.service;

import com.deloitte.room.controller.dto.SalaDTO;
import com.deloitte.room.model.Sala;
import com.deloitte.room.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaService {
    @Autowired
   private SalaRepository salaRepository;

    public Sala criar(Sala sala){
        if(salaRepository.existsByNome(sala.getNome())){
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }

        SalaDTO createdSala = new SalaDTO(sala.getID(), sala.getNome(), sala.getCapacidadeMaxima(), sala.getLocalizacao());

        return salaRepository.save(sala);

    }
    //rever anotação
    @Transactional(readOnly = true)
    public List<Sala> listarTodas(){
        return salaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sala buscarPorId(Long id){
        return salaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada."));
    }

    public Sala atualizar(Long id, Sala sala){
        if(!sala.getNome().equals(sala.getNome()) && salaRepository.existsByNome(sala.getNome())){
            throw new IllegalArgumentException("Já existe uma sala com esse nome");
        }

        SalaDTO createdSala = new SalaDTO(sala.getID(), sala.getNome(), sala.getCapacidadeMaxima(), sala.getLocalizacao());

        return salaRepository.save(sala);
    }

    public void deletar(Long id){
        Sala sala = buscarPorId(id);
        salaRepository.delete(sala);
    }
}
