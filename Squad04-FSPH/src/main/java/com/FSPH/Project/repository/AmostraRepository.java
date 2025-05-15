package com.FSPH.Project.repository;



import com.FSPH.Project.model.Amostra;
import com.FSPH.Project.model.TipoAmostra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, UUID> {
    List<Amostra> findByTipoAmostra(TipoAmostra tipoAmostra);
}
