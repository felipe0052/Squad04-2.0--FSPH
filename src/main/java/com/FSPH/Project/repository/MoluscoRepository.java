package com.FSPH.Project.repository;


import com.FSPH.Project.model.Molusco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MoluscoRepository extends JpaRepository<Molusco, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}
