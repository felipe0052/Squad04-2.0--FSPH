package com.FSPH.Project.service;

import com.FSPH.Project.model.LoteComum;
import com.FSPH.Project.repository.LoteComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoteComumService {

    @Autowired
    private LoteComumRepository loteComumRepository;

    public LoteComum criarLote(LoteComum lote) {
        String protocolo = gerarProtocoloLote();
        lote.setProtocolo(protocolo);
        lote.setDataCriacao(java.sql.Date.valueOf(LocalDate.now())); // define a data de criação
        return loteComumRepository.save(lote);
    }

    public List<LoteComum> listarLotes() {
        return loteComumRepository.findAll();
    }

    private String gerarProtocoloLote() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = loteComumRepository.countByProtocoloStartingWith(data);
        String sequencial = String.format("%05d", count + 1);
        return data + sequencial;
    }
}
