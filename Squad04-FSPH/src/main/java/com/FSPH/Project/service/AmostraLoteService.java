package com.FSPH.Project.service;

import com.FSPH.Project.model.Amostra;
import com.FSPH.Project.model.AmostraLote;
import com.FSPH.Project.model.Lote;
import com.FSPH.Project.repository.AmostraLoteRepository;
import com.FSPH.Project.repository.AmostraRepository;
import com.FSPH.Project.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AmostraLoteService {

    @Autowired
    private AmostraLoteRepository amostraLoteRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private AmostraRepository amostraRepository;

    // Adicionar Amostra já existente ao Lote
    public AmostraLote adicionarAmostraAoLote(UUID idLote, UUID idAmostra) {
        Optional<Lote> loteOpt = loteRepository.findById(idLote);
        Optional<Amostra> amostraOpt = amostraRepository.findById(idAmostra);

        if (loteOpt.isEmpty() || amostraOpt.isEmpty()) {
            throw new RuntimeException("Lote ou Amostra não encontrados.");
        }

        Lote lote = loteOpt.get();
        Amostra amostra = amostraOpt.get();

        AmostraLote amostraLote = new AmostraLote();
        amostraLote.setLote(lote);
        amostraLote.setAmostra(amostra);

        return amostraLoteRepository.save(amostraLote);
    }

    // Listar Amostras de um Lote
    public List<AmostraLote> listarAmostrasPorLote(UUID idLote) {
        return amostraLoteRepository.findByLoteIdLote(idLote);
    }

    // Remover a associação pelo ID da Amostra
    public void removerAmostraDoLote(UUID idAmostra) {
        Optional<AmostraLote> amostraLoteOpt = amostraLoteRepository.findByAmostraIdAmostra(idAmostra);

        if (amostraLoteOpt.isEmpty()) {
            throw new RuntimeException("Associação de Amostra ao Lote não encontrada.");
        }

        amostraLoteRepository.deleteByAmostraIdAmostra(idAmostra);
    }

}

