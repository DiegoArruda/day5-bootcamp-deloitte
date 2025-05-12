package com.deloitte.room.service;

import com.deloitte.room.controller.dto.ReservaDTO;
import com.deloitte.room.model.Reserva;
import com.deloitte.room.model.Sala;
import com.deloitte.room.repository.ReservaRepository;
import com.deloitte.room.repository.SalaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private SalaRepository salaRepository;

    public Reserva criarReserva(ReservaDTO dto){
        if(dto.getInicioReserva().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível reservar datas passadas");
        }

        if (!dto.getInicioReserva().isBefore(dto.getFimReserva())){
            throw new IllegalArgumentException("Data de início deve ser anterior à data de fim.");
        }

        List<Reserva> conflitos = reservaRepository
                .findBySalaIDAndInicioReservaLessThanEqualAndFimReservaGreaterThanEqual(dto.getSalaId(), dto.getFimReserva(),dto.getInicioReserva());

        if (!conflitos.isEmpty()){
            throw new IllegalArgumentException("Já existe uma reserva nesse horário");
        }

        Sala sala = salaRepository.findById(dto.getSalaId())
                .orElseThrow(()-> new EntityNotFoundException("Sala não encontrada"));

        Reserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setNomeResponsavel(dto.getNomeResponsavel());
        reserva.setInicioReserva(dto.getInicioReserva());
        reserva.setFimReserva(dto.getFimReserva());

        return reservaRepository.save(reserva);
    }

    public Reserva buscarPorId(Long id){
        return reservaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Reserva não encontrada."));
    }

    public List<Reserva> listarFuturas(){
        return reservaRepository.findByInicioReservaAfterOrderByInicioReservaAsc(LocalDateTime.now());
    }

    public List<Reserva> listarPorSala(Long salaId){
        return reservaRepository.findBySalaIDOrderByInicioReservaAsc(salaId);
    }

    public void deletar(Long id){
        Reserva reserva = buscarPorId(id);
        reservaRepository.delete(reserva);
    }
}
