package com.FSPH.Project.repository;



import com.FSPH.Project.model.Amostra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, UUID> {
    List<Amostra> findByTipoAmostraIgnoreCase(String tipoAmostra);
}
