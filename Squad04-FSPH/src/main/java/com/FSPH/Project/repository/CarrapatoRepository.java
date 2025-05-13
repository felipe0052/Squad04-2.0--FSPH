package com.FSPH.Project.repository;

import com.FSPH.Project.model.Carrapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CarrapatoRepository extends JpaRepository<Carrapato, Integer> {
    void deleteByAmostraIdAmostra(UUID id);
}
