package com.FSPH.Project.repository;

import com.FSPH.Project.model.Escorpiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EscorpiaoRepository extends JpaRepository<Escorpiao, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}
