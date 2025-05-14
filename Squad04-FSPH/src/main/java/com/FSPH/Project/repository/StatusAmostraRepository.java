package com.FSPH.Project.repository;

import com.FSPH.Project.model.StatusAmostra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusAmostraRepository extends JpaRepository<StatusAmostra, UUID> {

    StatusAmostra findByDescricao(String descricao);
}
