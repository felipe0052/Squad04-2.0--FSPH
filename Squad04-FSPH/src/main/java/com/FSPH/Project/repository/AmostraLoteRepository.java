package com.FSPH.Project.repository;

import com.FSPH.Project.model.AmostraLote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AmostraLoteRepository extends JpaRepository<AmostraLote, UUID> {
    List<AmostraLote> findByLoteIdLote(UUID idLote);

    Optional<AmostraLote> findByAmostraIdAmostra(UUID idAmostra);

    void deleteByAmostraIdAmostra(UUID idAmostra);

}
