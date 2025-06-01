package com.FSPH.Project.repository;

import com.FSPH.Project.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
    // REPOSITORIO DO LOTE

public interface LoteRepository extends JpaRepository<Lote, UUID> {

    List<Lote> findByTipoLoteIgnoreCase(String tipoLote);
    // Conta quantos lotes começam com um determinado prefixo no campo "protocolo"
    long countByProtocoloStartingWith(String prefix);
}
