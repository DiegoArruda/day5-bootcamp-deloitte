package com.deloitte.room.service;

import com.deloitte.room.controller.dto.SalaDTO;
import com.deloitte.room.model.Sala;
import com.deloitte.room.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    @Autowired
   private SalaRepository salaRepository;

    public Sala criar(SalaDTO dto){
        if(salaRepository.existsByNome(dto.getNome())){
            throw new IllegalArgumentException("Já existe uma sala com esse nome.");
        }

        Sala sala = new Sala();
        sala.setNome(dto.getNome());
        sala.setCapacidadeMaxima(dto.getCapacidade());
        sala.setLocalizacao(dto.getLocalizacao());

        return salaRepository.save(sala);

    }

    public List<Sala> listarTodas(){
        return salaRepository.findAll();
    }

    public Sala buscarPorId(Long id){
        return salaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada."));
    }

    public Sala atualizar(Long id, SalaDTO dto){
        Sala sala = buscarPorId(id);

        if(!sala.getNome().equals(dto.getNome()) && salaRepository.existsByNome(dto.getNome())){
            throw new IllegalArgumentException("Já existe uma sala com esse nome");
        }

        sala.setNome(dto.getNome());
        sala.setCapacidadeMaxima(dto.getCapacidade());
        sala.setLocalizacao(dto.getLocalizacao());

        return salaRepository.save(sala);
    }

    public void deletar(Long id){
        Sala sala = buscarPorId(id);
        salaRepository.delete(sala);
    }
}
