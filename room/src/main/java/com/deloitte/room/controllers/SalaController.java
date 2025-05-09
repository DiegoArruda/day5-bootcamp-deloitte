package com.deloitte.room.controllers;

import com.deloitte.room.DTO.SalaDTO;
import com.deloitte.room.models.Sala;
import com.deloitte.room.services.SalaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid SalaDTO dto){
        try{
            Sala sala = salaService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(sala);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Sala> listar(){
        return salaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            Sala sala = salaService.buscarPorId(id);
            return ResponseEntity.ok(sala);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody SalaDTO dto){
        try{
            Sala sala = salaService.atualizar(id,dto);
            return ResponseEntity.ok(sala);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        try{
            salaService.deletar(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
