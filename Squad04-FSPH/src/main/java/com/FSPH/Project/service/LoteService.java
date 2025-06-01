package com.FSPH.Project.service;

import com.FSPH.Project.model.Lote;
import com.FSPH.Project.model.TipoLote;
import com.FSPH.Project.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public Lote criarLote(Lote lote) {
        boolean temAmostras = lote.getAmostras() != null && !lote.getAmostras().isEmpty();
        boolean temLaminas = lote.getLaminas() != null && !lote.getLaminas().isEmpty();

        if (temAmostras && temLaminas) {
            throw new RuntimeException("Um lote não pode conter Amostras e Lâminas ao mesmo tempo.");
        }else{
            String protocolo = gerarProtocoloLote();
            lote.setProtocolo(protocolo);
            lote.setDataCriacao(java.sql.Date.valueOf(LocalDate.now())); // define a data de criação
            return loteRepository.save(lote);
        }
    }

    // Verificar se é Lote de Amostras baseado no Enum
    public boolean isLoteDeAmostras(Lote lote) {
        return TipoLote.AMOSTRAS.equals(lote.getTipoLote());
    }

    public boolean isLoteDeLaminas(Lote lote) {
        return TipoLote.LAMINAS.equals(lote.getTipoLote());
    }

    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }

    private String gerarProtocoloLote() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = loteRepository.countByProtocoloStartingWith(data);
        String sequencial = String.format("%05d", count + 1);
        return data + sequencial;
    }

    // Buscar um lote por ID
    public Optional<Lote> buscarPorId(UUID id) {
        return loteRepository.findById(id);
    }

    // Deletar um lote por ID
    public void deletarPorId(UUID id) {
        loteRepository.deleteById(id);
    }
}

