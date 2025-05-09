package com.deloitte.room.repositories;

import com.deloitte.room.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala,Long> {
    boolean existsByNome(String nome);
}
