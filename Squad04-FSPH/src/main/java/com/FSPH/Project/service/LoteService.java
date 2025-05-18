package com.FSPH.Project.service;

import com.FSPH.Project.model.*;
import com.FSPH.Project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public Lote criarLote (Lote lote) {
        lote = loteRepository.save(lote);

    return lote;
    }

    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<Lote> listarTodos() {
        return loteRepository.findAll();
    }

}

