package com.deloitte.room.repository;

import com.deloitte.room.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala,Long> {
    boolean existsByNome(String nome);
}
