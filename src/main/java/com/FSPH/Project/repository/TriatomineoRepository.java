package com.FSPH.Project.repository;


import com.FSPH.Project.model.Triatomineo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TriatomineoRepository extends JpaRepository<Triatomineo, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}

