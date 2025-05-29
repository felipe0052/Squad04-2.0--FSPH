package com.FSPH.Project.repository;

import com.FSPH.Project.model.Larva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LarvaRepository extends JpaRepository<Larva, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}
