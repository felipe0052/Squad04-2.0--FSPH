package com.FSPH.Project.repository;


import com.FSPH.Project.model.Flebotomineo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FlebotomineoRepository extends JpaRepository<Flebotomineo, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}
