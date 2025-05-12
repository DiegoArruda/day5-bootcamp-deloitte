package com.deloitte.room.controller;

import com.deloitte.room.controller.dto.ReservaDTO;
import com.deloitte.room.model.Reserva;
import com.deloitte.room.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> criarReserva(@RequestBody @Valid ReservaDTO dto){
        try{
            Reserva reserva = reservaService.criarReserva(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/futuras")
    public List<Reserva> listarReservasFuturas(){
        return reservaService.listarFuturas();
    }

    @GetMapping("/salas/{salaId}")
    public List<Reserva> listarPorSala(@PathVariable Long salaId){
        return reservaService.listarPorSala(salaId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        try{
            reservaService.deletar(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
